<template>
  <div class="app-container">
    <div :data="problem">
      <h3 v-text="problem.id + '.' + problem.title"></h3>
      <el-tabs type="card"
               v-loading.body="loading"
               element-loading-text="loading">
        <el-tab-pane>
          <span slot="label"><svg-icon icon-class="documentation" /> 题目描述</span>

          <!-- fixme -->
          <el-dialog title="收藏题目" width="25%" :visible.sync="dialogFormVisible">
            <div v-for="item in favoriteList">
              <label>{{ item.title }}</label>
              <el-switch
                :model="item"
                active-text="收藏"
                inactive-text="取消">
              </el-switch>
            </div>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
            </div>
          </el-dialog>

          <el-dropdown trigger="click" size="medium" split-button
                       :hide-on-click="false"
                       @click="showFavoriteDialog"
                       :loading="btnLoading">
            <svg-icon icon-class="star" /> 收藏

            <el-dropdown-menu slot="dropdown">
              <el-popover ref="popoverFavorite" placement="right" width="200" trigger="click">
                <el-form :model="newFavorite">
                  <el-row :gutter="20" style="padding-bottom: 8px">
                    <el-col :span="13">
                      <el-select size="mini" v-model="newFavorite.isPrivate" placeholder="请选择">
                        <el-option v-for="item in isPrivateOptions" :key="item.value" :label="item.label" :value="item.value" />
                      </el-select>
                    </el-col>
                    <el-col :span="11">
                      <el-button round size="mini" type="primary" @click="addFavorite" :loading="btnLoading">添加</el-button>
                    </el-col>
                  </el-row>
                  <el-form-item>
                    <el-input v-model="newFavorite.title" placeholder="收藏夹名称" />
                  </el-form-item>
                </el-form>
              </el-popover>
              <el-dropdown-item v-popover:popoverFavorite>添加收藏夹</el-dropdown-item>

              <router-link class="inlineBlock" to="/login/index">
                <el-dropdown-item divided >
                  <span style="display:block;">我的收藏夹</span>
                </el-dropdown-item>
              </router-link>
            </el-dropdown-menu>
          </el-dropdown>

          <el-container>
            <el-main>{{ problem.description }}</el-main>
            <el-aside width="250px">
              <ul class="list">
                <li class="list-item"><span>题目难度：</span>
                  <span class="right">
                    <el-tag v-if="problem.level === 1" type="success">简单</el-tag>
                    <el-tag v-else-if="problem.level === 2" type="warning">中等</el-tag>
                    <el-tag v-else type="danger">困难</el-tag>
                  </span>
                </li>

                <li class="list-item"><span>通过次数：</span>
                  <span class="right">
                  {{ problem.submitted }}
                  </span>
                </li>

                <li class="list-item"><span>通过次数：</span>
                  <span class="right">
                    {{ problem.accepted }}
                  </span>
                </li>
              </ul>
            </el-aside>
          </el-container>

          <div class="code-editor">
            <code-editor class="editor" ref="codeEditor" v-model="code" />
            <div class="code-button">
              <el-row :gutter="20">
                <el-col :span="2" :offset="16">
                  <el-button round
                             :loading="btnLoading"
                             @click.native.prevent="handleRunOrSubmit(false)">执行代码</el-button></el-col>
                <el-col :span="2" :offset="2">
                  <el-button type="primary" round
                             :loading="btnLoading"
                             @click.native.prevent="handleRunOrSubmit(true)">提交解答</el-button></el-col>
              </el-row>
            </div>
          </div>
        </el-tab-pane>

        <el-tab-pane>
          <span slot="label" @click="listSubmitCode"><svg-icon icon-class="code" /> 提交记录</span>
            <el-table :data="submitCodeList"
                      v-loading.body="listLoading"
                      element-loading-text="loading"
                      fit>
              <el-table-column label="提交时间"
                               align="center"
                               sortable
                               prop="submitTime">
                <template slot-scope="scope">
                  <i class="el-icon-time"></i>
                  {{ unix2CurrentTime(scope.row.submitTime) }}
                </template>
              </el-table-column>
              <el-table-column label="状态"
                               prop="status"
                               align="center">
                <template slot-scope="scope">
                  <el-tag v-if="scope.row.status === 1" type="success">通过</el-tag>
                  <el-tag v-else-if="scope.row.status === 2" type="warning">xx</el-tag>
                  <el-tag v-else type="danger">xxx</el-tag>
                </template>
              </el-table-column>
              <el-table-column label="消耗时间"
                               prop="timeUsed"
                               sortable
                               align="center" />
              <el-table-column label="消耗内存"
                               prop="memoryUsed"
                               sortable
                               align="center" />
              <el-table-column label="语言"
                               prop="language"
                               align="center" />
            </el-table>
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="listQuery.page"
              :page-size="listQuery.size"
              :total="total"
              :page-sizes="[10, 30, 50, 100]"
              layout="total, sizes, prev, pager, next, jumper">
            </el-pagination>
        </el-tab-pane>

        <el-tab-pane disabled>
          <span slot="label">
            <el-tooltip content="即将到来" placement="top-start">
              <svg-icon icon-class="chat" />
            </el-tooltip> 社区讨论
          </span>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import { Base64 } from 'js-base64'
  import { run as runCode, submit as submitCode, listSubmitCode } from '@/api/code'
  import { list as listUserFavorite, add as addFavorite, addProblem as addProblem2Favorite, removeProblem as removeProblemFromFavorite } from '@/api/favorite'
  import { detail as getProblemDetail } from '@/api/problem'
  import { unix2CurrentTime } from '@/utils'
  import CodeEditor from '@/components/CodeEditor'

  export default {
    components: { CodeEditor },
    created() {
      this.getProblemDetail()
    },
    data() {
      return {
        loading: false,
        btnLoading: false,
        problem: {
          id: this.$route.params.id,
          title: null,
          level: null,
          tags: null,
          accepted: null,
          submitted: null,
          description: null
        },
        code: '',
        submitCodeList: [], // 提交记录列表
        total: 0,
        listLoading: false,
        listQuery: {
          page: 1, // 页码
          size: 30 // 每页数量
        },
        dialogFormVisible: false,
        favoriteList: null,
        favoriteListQuery: {
          page: 1,
          size: 30
        },
        isPrivateOptions: [{ label: '私有', value: true }, { label: '公开', value: false }],
        newFavorite: {
          title: null,
          userId: null,
          isPrivate: true
        },
        a: true
      }
    },
    computed: {
      ...mapGetters([
        'userId',
        'codeMirrorLanguage'
      ])
    },
    methods: {
      unix2CurrentTime,
      showFavoriteDialog() {
        this.btnLoading = true
        listUserFavorite(this.userId, this.favoriteListQuery).then(response => {
          this.favoriteList = response.data.list
          this.dialogFormVisible = true
          this.btnLoading = false
        })
      },
      addProblem2Favorite() {
        this.btnLoading = true
        addProblem2Favorite().then(() => {
          this.$message.success('收藏成功')
          this.btnLoading = false
        })
      },
      addFavorite() {
        this.btnLoading = true
        this.newCollection.userId = this.userId
        addFavorite(this.newCollection).then(() => {
          this.$message.success('成功添加收藏夹')
          this.btnLoading = false
        })
      },
      getProblemDetail() {
        this.loading = true
        getProblemDetail(this.problem.id).then(response => {
          this.problem = response.data
          this.loading = false
        })
      },
      listSubmitCode() {
        // 查询列表
        this.listLoading = true
        listSubmitCode(this.problem.id, this.listQuery).then(response => {
          this.submitCodeList = response.data.list
          this.total = response.data.total
          this.listLoading = false
        })
      },
      handleRunOrSubmit(status) {
        this.btnLoading = true
        const codeForm = {
          problemId: this.problem.id,
          userId: this.userId,
          code: Base64.encode(this.code),
          language: this.codeMirrorLanguage.name
        }
        if (!status) {
          runCode(codeForm).then(() => {
            this.$message.success('run成功')
            this.btnLoading = false
          })
        } else {
          submitCode(codeForm).then(() => {
            this.$message.success('submit成功')
            this.btnLoading = false
          })
        }
      },
      handleSizeChange(size) {
        // 改变每页数量
        this.listQuery.size = size
        this.handleFilter()
      },
      handleCurrentChange(page) {
        // 改变页码
        this.listQuery.page = page
        this.listSubmitCode()
      },
      handleFilter() {
        // 查询事件
        this.listQuery.page = 1
        this.listSubmitCode()
      }
    }
  }
</script>

<style lang="scss" scoped>
  .list {
    border-left-style: solid;
    border-left-color: #F5F5F5;
    border-left-width: 2px;
    border-right-style: solid;
    border-right-color: #F5F5F5;
    border-right-width: 2px;
    box-sizing: border-box;
  }
  .list-item {
    padding: 10px 0;
    list-style-type: none;
    font-size: 14px;
    .right {
      float: right;
      padding-right: 30px;
    }
  }
  .code-editor {
    border-top-style: solid;
    border-top-color: #F5F5F5;
    border-top-width: 1px;
    box-sizing: border-box;
    .code-button {
      padding: 10px 0;
    }
  }
</style>
