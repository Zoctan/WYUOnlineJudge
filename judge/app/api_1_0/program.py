#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from flask import request, jsonify
from . import api, decorator
from .q_judge import QJudge, ProblemBean


@api.route('/run', methods=['POST'])
@decorator.json_required
def run_program():
    problem = ProblemBean(
        problemId=request.json.get('problemId'),
        userId=request.json.get('userId'),
        language=request.json.get('language'),
        code=request.json.get('code'),
        nums=request.json.get('nums', 1)
    )

    result_list = QJudge(problem).run()

    return jsonify(result_list)
