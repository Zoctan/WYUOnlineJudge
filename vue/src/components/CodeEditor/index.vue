<template>
  <div class="code-editor">
    <div class="editor-option">
      <el-dropdown trigger="click" size="medium" class="language-dropdown" split-button type="primary" @command="setLanguage">
        语言
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item v-for="item in language" :disabled="item.name === defaultLanguage.name" v-text="item.name" :command="item" :key="item.name" />
        </el-dropdown-menu>
      </el-dropdown>
      <el-dropdown trigger="click" size="medium" class="theme-dropdown" split-button type="primary" @command="setTheme">
        主题
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item v-for="item in theme" :disabled="item === defaultTheme" v-text="item" :command="item" :key="item" />
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <textarea ref="codeEditor"></textarea>
  </div>
</template>

<script>
import CodeMirror from 'codemirror'
import 'codemirror/addon/lint/lint'
import 'codemirror/addon/lint/lint.css'
import 'codemirror/lib/codemirror.css'

import 'codemirror/addon/display/autorefresh'

import 'codemirror/addon/display/fullscreen.css'
import 'codemirror/addon/display/fullscreen'

import 'codemirror/addon/edit/matchbrackets'

import 'codemirror/addon/hint/show-hint.css'
import 'codemirror/addon/hint/show-hint'
import 'codemirror/addon/hint/anyword-hint'

import 'codemirror/theme/eclipse.css'
import 'codemirror/theme/material.css'
import 'codemirror/theme/ambiance.css'

import 'codemirror/mode/clike/clike'
import 'codemirror/mode/javascript/javascript'
import 'codemirror/mode/php/php'
import 'codemirror/mode/python/python'
import 'codemirror/mode/ruby/ruby'
import 'codemirror/mode/go/go'

export default {
  name: 'codeEditor',
  data() {
    return {
      codeEditor: false,
      language: [ // codemirror/mode/meta.js
        { name: 'C', mime: 'text/x-csrc', mode: 'clike' },
        { name: 'C++', mime: 'text/x-c++src', mode: 'clike' },
        { name: 'C#', mime: 'text/x-csharp', mode: 'clike' },
        { name: 'Java', mime: 'text/x-java', mode: 'clike' },
        { name: 'JavaScript', mimes: ['text/javascript', 'text/ecmascript', 'application/javascript', 'application/x-javascript', 'application/ecmascript'], mode: 'javascript' },
        { name: 'PHP', mimes: ['text/x-php', 'application/x-httpd-php', 'application/x-httpd-php-open'], mode: 'php' },
        { name: 'Python3', mime: 'text/x-python', mode: 'python' },
        { name: 'Ruby', mime: 'text/x-ruby', mode: 'ruby' },
        { name: 'Go', mime: 'text/x-go', mode: 'go' }
      ],
      theme: ['eclipse', 'material', 'ambiance'],
      defaultLanguage: { name: 'C', mime: 'text/x-csrc', mode: 'clike' },
      defaultTheme: 'eclipse'
    }
  },
  props: ['value'],
  watch: {
    value(value) {
      const editor_value = this.codeEditor.getValue()
      if (value !== editor_value) {
        this.codeEditor.setValue(this.value)
      }
    }
  },
  mounted() {
    this.codeEditor = CodeMirror.fromTextArea(this.$refs.codeEditor, {
      theme: this.defaultTheme, // 设置主题
      mode: this.defaultLanguage.mime, // 设置语言
      lint: true,
      autoRefresh: true,
      lineNumbers: true, // 显示行号
      // fullScreen: true, // 全屏模式
      lineWrapping: true, // 代码折叠
      matchBrackets: true, // 括号匹配
      foldGutter: true,
      gutters: ['CodeMirror-linenumbers', 'CodeMirror-foldgutter'],
      extraKeys: {
        'Ctrl-Space': 'autocomplete', // ctrl-space唤起智能提示
        'F11': cm => cm.setOption('fullScreen', !cm.getOption('fullScreen'))
      }
    })
    this.codeEditor.setValue(this.value)
    this.codeEditor.on('change', cm => {
      this.$emit('changed', cm.getValue())
      this.$emit('input', cm.getValue())
    })
  },
  methods: {
    getValue() {
      return this.codeEditor.getValue()
    },
    setTheme(theme) {
      this.defaultTheme = theme
      return this.codeEditor.setOption('theme', theme)
    },
    setLanguage(language) {
      this.defaultLanguage = language
      return this.codeEditor.setOption('mode', language.mimes ? language.mimes[0] : language.mime)
    }
  }
}
</script>


<style scoped>
  .code-editor {
    height: 100%;
    position: relative;
  }
  .code-editor >>> .editor-option {
    margin: 12px auto;
  }
  .code-editor >>> .CodeMirror {
    height: auto;
    min-height: 300px;
    border: 1px solid gainsboro;
    font-size: 15px;
  }
  .code-editor >>> .CodeMirror-scroll {
    min-height: 300px;
  }
</style>
