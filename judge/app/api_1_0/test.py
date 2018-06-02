#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from app.api_1_0 import q_judge
import base64
import os
from flask import jsonify
from . import api
from .q_judge import QJudge, ProblemBean


@api.route('/test', methods=['GET'])
def test_run_program():
    return jsonify({'result': test_status()})


def test_status():
    status = ['AC', 'CE', 'DSC', 'MLE', 'MLE(stack)', 'OLE', 'RE', 'TLE', 'WA']
    total_result_list = []
    for i in status:
        with open(os.path.join('../../test_dir', i, 'test.cpp'), 'rb') as f:
            base64_code = base64.b64encode(f.read())
        result_list = QJudge(ProblemBean(problem_id='test_status',
                                         user_id=i,
                                         language='C++',
                                         base64_code=base64_code,
                                         test_nums=1)).run()
        total_result_list.append(result_list)

    return total_result_list


def test_languages():
    languages = ['C', 'C++', 'C#', 'Go', 'Java', 'Python3', 'Ruby', 'JavaScript']  # ruby and js error output
    user_id = 'Languages'
    total_result_list = []
    for i in languages:
        with open(os.path.join('../../test_dir', user_id, i), 'rb') as f:
            base64_code = base64.b64encode(f.read())
        result_list = q_judge.QJudge(ProblemBean(problem_id='test_languages',
                                                 user_id=i,
                                                 language=i,
                                                 base64_code=base64_code)).run()
        total_result_list.append(result_list)

    return total_result_list
