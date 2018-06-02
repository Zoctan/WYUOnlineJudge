#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from os import getenv
from app import create_app
from flask_script import Server, Manager

app = create_app(getenv('FLASK_CONFIG') or 'development')
manager = Manager(app)

manager.add_command("runserver", Server(host="0.0.0.0", port=20000))

if __name__ == '__main__':
    manager.run()
