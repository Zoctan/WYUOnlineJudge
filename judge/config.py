#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import os

setting = 'charset=utf8'


class Config:
    SECRET_KEY = os.environ.get('SECRET_KEY') or \
                 'the quick brown fox jumps over the lazy dog'
    SQLALCHEMY_TRACK_MODIFICATIONS = True

    @staticmethod
    def init_app(app):
        pass


class DevelopmentConfig(Config):
    pass

class TestingConfig(Config):
    pass

class ProductionConfig(Config):
    pass

config = {
    'development': DevelopmentConfig,
    'testing': TestingConfig,
    'production': ProductionConfig
}
