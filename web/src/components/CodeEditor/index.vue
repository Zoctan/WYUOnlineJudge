<template>
  <div class="code-editor">
    <div class="editor-option">
      <el-row>
        <el-col :span="20">
          <el-select filterable v-model="languageSelected" placeholder="语言" @change="setLanguage">
            <el-option v-for="item in language"
                       :key="item.name"
                       :label="item.name"
                       :value="item.name" />
          </el-select>
        </el-col>
        <el-col :span="4">
          <el-dropdown trigger="click" @command="setTheme">
            <el-button>
              <svg-icon icon-class="editor_theme" /> 主题 <i class="el-icon-arrow-down el-icon--right"></i>
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item v-for="item in theme" :disabled="codeMirror.theme !== undefined && item === codeMirror.theme" v-text="item" :command="item" :key="item" />
            </el-dropdown-menu>
          </el-dropdown>
        </el-col>
      </el-row>
    </div>
    <textarea ref="codeEditor"></textarea>
  </div>
</template>

<script>
  import { mapState } from 'vuex'
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
        languageSelected: 'C',
        theme: ['eclipse', 'material', 'ambiance']
      }
    },
    computed: {
      ...mapState({
        codeMirror: state => state.codeMirror
      })
    },
    props: ['value'],
    watch: {
      value(value) {
        if (value !== this.codeEditor.getValue()) {
          this.codeEditor.setValue(this.value)
        }
      }
    },
    mounted() {
      this.codeEditor = CodeMirror.fromTextArea(this.$refs.codeEditor, {
        lint: true,
        autoRefresh: true,
        lineNumbers: true, // 显示行号
        lineWrapping: true, // 代码折叠
        matchBrackets: true, // 括号匹配
        foldGutter: true,
        gutters: ['CodeMirror-linenumbers', 'CodeMirror-foldgutter'],
        extraKeys: {
          'Ctrl-Space': 'autocomplete', // ctrl-space唤起智能提示
          'F11': cm => cm.setOption('fullScreen', !cm.getOption('fullScreen'))
        }
      })
      // 读取Cookie
      this.languageSelected = this.codeMirror.language !== null ? this.codeMirror.language.name : 'C'
      this.setLanguage(this.languageSelected)
      this.setTheme(this.codeMirror.theme !== undefined ? this.codeMirror.theme : 'eclipse')
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
      setLanguage(language) {
        // 设置语言
        // todo: no for
        this.language.filter(i => i.name === language).map(i => {
          console.info(i.name)
          this.$store.dispatch('SetLanguage', i)
          this.codeEditor.setOption('mode', i.mimes ? i.mimes[0] : i.mime)
        })
      },
      setTheme(theme) {
        // 设置主题
        this.$store.dispatch('SetTheme', theme)
        this.codeEditor.setOption('theme', theme)
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
