#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from flask import request, jsonify
from . import api, decorator
from .q_judge import QJudge, ProblemBean


@api.route('/run', methods=['POST'])
@decorator.json_required
def run_program():
    problem = ProblemBean(
        problem_id=request.json.get('problem_id'),
        user_id=request.json.get('user_id'),
        language=request.json.get('language'),
        code=request.json.get('code'),
        test_nums=request.json.get('test_nums')
    )

    result_list = QJudge(problem).run()

    return jsonify({'result': result_list})
