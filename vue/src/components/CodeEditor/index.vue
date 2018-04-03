<template>
  <div class="code-editor">
    <textarea ref="textarea"></textarea>
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

import 'codemirror/mode/javascript/javascript'
import 'codemirror/mode/python/python'
import 'codemirror/mode/clike/clike'

export default {
  name: 'codeEditor',
  data() {
    return {
      codeEditor: false
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
    this.codeEditor = CodeMirror.fromTextArea(this.$refs.textarea, {
      theme: 'eclipse', // 设置主题
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

        'F11': function(cm) {
          cm.setOption('fullScreen', !cm.getOption('fullScreen'))
        }
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
      return this.codeEditor.setOption('theme', theme)
    }
  }
}
</script>


<style scoped>
  .code-editor{
    height: 100%;
    position: relative;
  }
  .code-editor >>> .CodeMirror {
    height: auto;
    min-height: 300px;
  }
  .code-editor >>> .CodeMirror-scroll{
    min-height: 300px;
  }
</style>
