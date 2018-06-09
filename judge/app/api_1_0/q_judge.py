#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import _judger
import os
import subprocess
import base64

my_dir2 = '/run/media/zoc/Data/github/WYUOnlineJudge/judge/'
my_dir = '/root/WYUOnlineJudge/judge/'

config = {
    # 工作目录（用于存放各个用户提交的代码）
    'work_dir': my_dir + 'work_dir',
    # 答案目录
    'answer_dir': my_dir + 'answer_dir'
}


class QJudge:
    RESULT_STATUS = {
        100: 'Accepted',
        0: 'Run Successfully',
        1: 'CPU Time Limit Exceeded',
        2: 'Real Time Limit Exceeded',
        3: 'Memory Limit Exceeded',
        4: 'Runtime Error',
        5: 'System Error',
        6: 'Output Limit',
        7: 'Presentation Error',
        8: 'Compile Error',
        -1: 'Wrong Answer',
        -2: 'Error Fork Failed',
        -3: 'Error Pthread Failed',
        -4: 'Error Wait Failed',
        -5: 'Error Root Required',
        -6: 'Error Load Seccomp Failed',
        -7: 'Error SetRLimit Failed',
        -8: 'Error Dup2 Failed',
        -9: 'Error Set Uid Failed',
        -10: 'Error Execute Failed',
        -11: 'Error SPJ'
    }

    def __init__(self, problem):
        self.problem = problem
        self.result_list = []
        # 设置用户临时目录
        self.user_dir_path = os.path.join(config['work_dir'], str(self.problem['userId']))
        self.user_program_path = None
        # 设置err错误文件，存放用户程序出现的错误
        self.user_err_file_path = os.path.join(self.user_dir_path, 'err')
        # 如果该目录不存在，先创建
        if not os.path.exists(self.user_dir_path):
            os.makedirs(self.user_dir_path)
        # 将传过来的代码保存到文件
        self._save()

    """
    不同的语言保存成不同的后缀文件
    """

    def _save(self):
        language_file_map = {
            'C': 'Solution.c',
            'C++': 'Solution.cpp'
        }
        self.user_program_path = os.path.join(self.user_dir_path, language_file_map[self.problem['language']])
        with open(self.user_program_path, 'wb') as f:
            code = base64.b64decode(self.problem['code'])
            f.write(code)

    """
    编译用户的代码
    """

    def _compile(self):
        command = {
            'C': 'gcc Solution.c -o Solution -Wall -lm -O2 -std=c11 -lseccomp -DONLINE_JUDGE',
            'C++': 'g++ Solution.cpp -o Solution -std=c++11 -Wall -lm -O2 -lseccomp -DONLINE_JUDGE'
        }

        sub = subprocess.Popen(command[self.problem['language']],
                               shell=True,
                               cwd=self.user_dir_path,
                               stdout=subprocess.PIPE,
                               stderr=subprocess.PIPE)
        output, error = sub.communicate()
        # 编译出错
        if sub.returncode != 0:
            with open(self.user_err_file_path, 'wb') as f:
                f.write(error)
            return False
        else:
            return True

    """
    运行一个测试用例
    """

    def _run_one(self, answer_in_path, answer_out_path):
        # 用户程序输出路径
        user_out_file_path = os.path.join(self.user_dir_path, 'out')

        rst = _judger.run(max_cpu_time=1000,
                          max_real_time=2000,
                          max_memory=128 * 1024 * 1024,
                          max_process_number=200,
                          max_output_size=10000,
                          max_stack=32 * 1024 * 1024,
                          # five args above can be _judger.UNLIMITED
                          exe_path=self.user_program_path,
                          input_path=answer_in_path,
                          output_path=user_out_file_path,
                          error_path=self.user_err_file_path,
                          args=[],
                          # can be empty list
                          env=[],
                          log_path="judger.log",
                          # can be None
                          seccomp_rule_name="c_cpp",
                          uid=0,
                          gid=0)
        print(rst)
        # 运行成功才评测
        if rst['result'] == 0:
            after_judge = self._judge(user_out_file_path, answer_out_path)
            # 删除用户答案（因为可能存在多个测试用例）
            os.remove(user_out_file_path)
            # 答案不对
            if after_judge != 0:
                return {'result': after_judge}
        return rst

    """
    用户答案和标准答案进行对比
    """

    def _judge(self, user_out_file_path, answer_out_file_path):
        with open(answer_out_file_path) as f:
            answer_out_list = f.readlines()
        with open(user_out_file_path) as f:
            user_out_list = f.readlines()
        if len(answer_out_list) < len(user_out_list):
            return 6
        elif len(answer_out_list) > len(user_out_list):
            return -1
        if all([x.strip() == y.strip() for x, y in zip(answer_out_list, user_out_list)]):
            return 100
        else:
            return -1

    """
    运行用户代码，并进行评测
    """

    def run(self):
        if not self._compile():
            self.result_list.append(ResultBean(problem=self.problem, status=8).to_json())
            return self.result_list
        # 依次运行测试用例
        for i in range(1, self.problem['nums'] + 1):
            # 标准答案输入
            answer_in_path = os.path.join(config['answer_dir'], str(self.problem['problemId']), str(i) + '.in')
            # 标准答案输出
            answer_out_path = os.path.join(config['answer_dir'], str(self.problem['problemId']), str(i) + '.out')
            # 可能题目的测试用例不够
            if os.path.isfile(answer_in_path) and os.path.isfile(answer_out_path):
                rst = self._run_one(answer_in_path, answer_out_path)
                if rst['result'] != 0 or rst['result'] != 100:
                    with open(self.user_err_file_path) as f:
                        result_bean = ResultBean(problem=self.problem,
                                                 status=rst['result'],
                                                 error=f.read(),
                                                 timeUsed=rst['real_time'],
                                                 memoryUsed=rst['memory'])
                else:
                    result_bean = ResultBean(problem=self.problem,
                                             status=rst['result'],
                                             timeUsed=rst['real_time'],
                                             memoryUsed=rst['memory'])
                self.result_list.append(result_bean.to_json())
            else:
                print('题目Id：{}，没有测试用例{}.in'.format(self.problem['problemId'], i))
                break
        return self.result_list

    """
    运行完毕后清除用户目录（故暂时不能用于多题目同时评测）
    """

    def __del__(self):
        __import__("shutil").rmtree(self.user_dir_path)
        pass


class ProblemBean:
    def __init__(self, **kwargs):
        # 题目Id，对于比赛题目=比赛Id_题目Id
        self.problemId = kwargs.get('problemId')
        # 用户Id
        self.userId = kwargs.get('userId')
        # 代码语言
        self.language = kwargs.get('language')
        # Base64压缩的代码
        self.code = kwargs.get('code')
        # 测试用例个数
        self.nums = kwargs.get('nums', 1)

    def __getitem__(self, key):
        return self.__dict__[key]

    def to_json(self):
        return self.__dict__


class ResultBean:
    def __init__(self, problem, **kwargs):
        # 和答案对比的结果
        self.status = kwargs.get('status')
        # 出现错误时的信息
        self.error = kwargs.get('error')
        # 消耗时间 MS
        self.timeUsed = kwargs.get('timeUsed', 'N/A')
        # 消耗内存 KB
        self.memoryUsed = kwargs.get('memoryUsed', 'N/A')

        self.problemId = problem['problemId']
        self.userId = problem['userId']
        self.language = problem['language']

    def __getitem__(self, key):
        return self.__dict__[key]

    def to_json(self):
        return self.__dict__


if __name__ == '__main__':
    with open(my_dir + 'answer_dir/1/1.c', 'rb') as f:
        base64_code = base64.b64encode(f.read())
        result_list = QJudge(ProblemBean(problemId=1,
                                         userId=1,
                                         language='C',
                                         code=base64_code,
                                         nums=1)).run()
        print(result_list)
