# !/usr/bin/python3

import pymysql

# 打开数据库连接
db = pymysql.connect("localhost", "root", "root", "api_dev", use_unicode=True, charset="utf8")

# 使用cursor()方法获取操作游标 
cursor = db.cursor()

try:
    # 执行SQL语句
    cursor.execute("SELECT id, tags FROM problem")
    # 获取所有记录列表
    results = cursor.fetchall()
    for row in results:
        id = row[0]
        tags = row[1]
        tagList = tags.split(',')
        list = []
        if id not in [1, 2, 3, 4]:
            for i in tagList:
                if 'Map' == i:
                    list.append('映射')
                if 'Geometry' == i:
                    list.append('几何')
                if 'Binary Indexed Tree' == i:
                    list.append('树状数组')
                elif 'Segment Tree' == i:
                    list.append('线段树')
                elif 'Queue' == i:
                    list.append('队列')
                elif 'Brainteaser' == i:
                    list.append('谜题')
                elif 'Sort' == i:
                    list.append('排序')
                elif 'Tree' == i:
                    list.append('树')
                elif 'Reservoir Sampling' == i:
                    list.append('蓄水池采样')
                elif 'Depth-first Search' == i:
                    list.append('深度搜索')
                elif 'Dynamic Programming' == i:
                    list.append('动态规划')
                elif 'Greedy' == i:
                    list.append('贪心')
                elif 'Heap' == i:
                    list.append('堆')
                elif 'Bit Manipulation' == i:
                    list.append('位运算')
                elif 'Binary Search' == i:
                    list.append('二分搜索')
                elif 'Union Find' == i:
                    list.append('并查集')
                elif 'Divide and Conquer' == i:
                    list.append('分治')
                elif 'Design' == i:
                    list.append('设计')
                elif 'Math' == i:
                    list.append('数学')
                elif 'Graph' == i:
                    list.append('图')
                elif 'Breadth-first Search' == i:
                    list.append('广度搜索')
                elif 'Topological Sort' == i:
                    list.append('拓扑排序')
                elif 'Backtracking' == i:
                    list.append('回溯')
                elif 'String' == i:
                    list.append('字符串')
                elif 'Two Pointers' == i:
                    list.append('双指针')
                elif 'Minimax' == i:
                    list.append('极小化极大')
                elif 'Array' == i:
                    list.append('数组')
                elif 'Stack' == i:
                    list.append('栈')
                elif 'Binary Search Tree' == i:
                    list.append('二叉树')
                elif 'Hash Table' == i:
                    list.append('哈希表')
                elif 'Linked List' == i:
                    list.append('链表')
                elif 'Memoization' == i:
                    list.append('记忆化')
                elif 'Trie' == i:
                    list.append('前缀树')
                else:
                    print('!!!! => {}'.format(i))
            if list:
                tags = ' '.join(list)
            print("id => {}, tagList = {}, tags = {}".format(id, tagList, tags))
            # SQL 更新语句
            sql = "UPDATE problem SET tags = '%s' WHERE id = '%d'" % (tags, id)
            try:
                # 执行sql语句
                cursor.execute(sql)
                # 提交到数据库执行
                db.commit()
            except Exception as e:
                # 如果发生错误则回滚
                db.rollback()
                print(e)
except:
    print("Error: unable to fetch data")

# 关闭数据库连接
db.close()
