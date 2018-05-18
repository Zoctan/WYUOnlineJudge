<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="19">
        <el-card class="box-card">
          <el-row>
            <el-col :span="21">
              <el-button @click="doSearch" icon="el-icon-search" circle size="small" />
              <el-input v-model="searchData" placeholder="搜索题目" style="width:200px" />
              <el-button v-if="searchData !== null" @click="noSearch" icon="el-icon-close" circle size="small" />
            </el-col>
            <el-col :span="3">
              <el-button @click="doSearch">随机开始 <svg-icon icon-class="random" /></el-button>
            </el-col>
          </el-row>
          <el-table :data="problemList"
                    v-loading.body="listLoading"
                    element-loading-text="loading"
                    stripe>
            <el-table-column label="#"
                             align="center"
                             width="50">
              <template slot-scope="scope">
                <span>{{ getIndex(scope.$index) }}</span>
              </template>
            </el-table-column>
            <el-table-column show-overflow-tooltip
                             label="题目">
              <template slot-scope="scope">
                <router-link v-if="hasPermission('problem:detail')" :to="{name: '题目详情', params: {id: scope.row.id}}">
                  <span class="hover">{{ scope.row.title }}</span>
                </router-link>
                <span class="hover" v-else @click="noLoginTip">{{ scope.row.title }}</span>
              </template>
            </el-table-column>
            <el-table-column label="标签"
                             prop="tag"
                             :filters="tags"
                             :filter-method="filterTag"
                             width="250">
              <template slot-scope="scope">
                <span>{{ scope.row.tags.split(' ').join(' / ') }}</span>
              </template>
            </el-table-column>
            <el-table-column label="难度"
                             sortable
                             prop="level"
                             width="100"
                             :filters="[{text: '简单', value: 1}, {text: '中等', value: 2}, {text: '困难', value: 3}]"
                             :filter-method="filterLevel">
              <template slot-scope="scope">
                <el-tag v-if="scope.row.level === 1" type="success" size="mini">简单</el-tag>
                <el-tag v-else-if="scope.row.level === 2" type="warning" size="mini">中等</el-tag>
                <el-tag v-else type="danger" size="mini">困难</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="通过率"
                             prop="accepted/submitted"
                             sortable
                             align="center"
                             width="100">
              <template slot-scope="scope">
                <span>{{ scope.row.submitted === 0 ? '0%' : (scope.row.accepted/scope.row.submitted).toFixed(2) * 100 + '%' }}</span>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            background
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="listQuery.page"
            :page-size="listQuery.size"
            :total="total"
            :page-sizes="[10, 20, 40, 100]"
            layout="total, sizes, prev, pager, next, jumper" />
        </el-card>
      </el-col>

      <el-col :span="5">
        <el-card class="box-card record">
          <span class="card-title"><svg-icon icon-class="record" /> 记录</span>
          <el-row type="flex" class="card-row" justify="space-between">
            <el-col :span="16"><svg-icon icon-class="right" /> 已解决</el-col>
            <el-col :span="8"><el-tag type="info" size="mini">123/456</el-tag></el-col>
          </el-row>

          <el-row type="flex" class="card-row" justify="space-between">
            <el-col :span="16"><svg-icon icon-class="easy" /> 简单</el-col>
            <el-col :span="8"><el-tag type="success" size="mini">1</el-tag></el-col>
          </el-row>

          <el-row type="flex" class="card-row" justify="space-between">
            <el-col :span="16"><svg-icon icon-class="medium" /> 中等</el-col>
            <el-col :span="8"><el-tag type="warning" size="mini">2</el-tag></el-col>
          </el-row>

          <el-row type="flex" class="card-row" justify="space-between">
            <el-col :span="16"><svg-icon icon-class="hard" /> 困难</el-col>
            <el-col :span="8"><el-tag type="danger" size="mini">3</el-tag></el-col>
          </el-row>
        </el-card>

        <el-card class="box-card classification" style="margin-top: 12px">
          <span class="card-title"><svg-icon icon-class="classification" /> 题目分类</span>
          <div class="tag">
            <span v-for="tag in tags" style="margin-right: 5px">
              <el-badge :value="tagsNum.get(tag.text)" :max="99" class="item">
                <el-tag>{{ tag.text }}</el-tag>
              </el-badge>
            </span>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import { list as getProblemList } from '@/api/problem'
  import { noLoginTip } from '@/utils/Tip'

  export default {
    created() {
      this.getProblemList()
    },
    data() {
      return {
        problemList: [], // 题目列表
        tags: [], // 标签列表
        tagsNum: null,
        listLoading: false, // 数据加载等待动画
        total: 0, // 数据总数
        listQuery: {
          page: 1, // 页码
          size: 20 // 每页数量
        },
        searchData: null, // 搜索内容
        search: {
          isSearch: false, // 是否进行过搜索
          beforeSearchList: [] // 搜索前的数据
        },
        btnLoading: false // 按钮等待动画
      }
    },
    methods: {
      noLoginTip,
      filterTag(value, row) {
        return row.tags.indexOf(value) !== -1
      },
      filterLevel(value, row) {
        return row.level === value
      },
      getProblemList() {
        // 查询列表
        this.listLoading = true
        getProblemList(this.listQuery).then(response => {
          this.problemList = response.data.list
          this.total = response.data.total
          this.listLoading = false
          this.getTagsFromProblemList()
        })
      },
      getTagsFromProblemList() {
        // 从题目列表获得过滤标签及其数目
        if (this.tagsNum === null) {
          this.tagsNum = new Map()
        }
        this.problemList.forEach((problem) => {
          const tagList = problem.tags.split(' ')
          tagList.forEach(tag => {
            if (!this.tagsNum.has(tag)) {
              this.tagsNum.set(tag, 1)
            } else {
              const num = this.tagsNum.get(tag)
              this.tagsNum.set(tag, num + 1)
            }
          })
        })
        this.tagsNum.forEach((value, key, map) => {
          const has = this.tags.filter(_tag => _tag.text === key).length > 0
          if (!has) {
            this.tags.push({ text: key, value: key })
          }
        })
      },
      handleSizeChange(size) {
        // 改变每页数量
        this.listQuery.size = size
        this.handleFilter()
      },
      handleCurrentChange(page) {
        // 改变页码
        this.listQuery.page = page
        this.getProblemList()
      },
      handleFilter() {
        // 查询事件
        this.listQuery.page = 1
        this.getProblemList()
      },
      getIndex(index) {
        // 表格序号
        return (this.listQuery.page - 1) * this.listQuery.size + index + 1
      },
      noSearch() {
        this.searchData = null
        if (this.search.isSearch) {
          this.problemList = this.search.beforeSearchList
          this.search.beforeSearchList = []
          this.search.isSearch = false
        }
      },
      doSearch() {
        if (this.searchData == null) {
          this.$message({
            message: '搜索内容不能为空哦！',
            type: 'danger'
          })
        } else {
          const resultList = []
          this.problemList.forEach(i => {
            if (i.title.indexOf(this.searchData) !== -1) {
              resultList.push(i)
            }
          })
          if (resultList.length === 0) {
            this.$message({
              message: '找不到相关内容哦！',
              type: 'warning'
            })
          } else {
            this.search.beforeSearchList = this.problemList
            this.search.isSearch = true
            this.problemList = resultList
          }
        }
      }
    }
  }
</script>


<style lang="scss" scoped>
  .record {
    padding-top: 10px;
  }
  .classification {
    padding-top: 10px;
    .tag {
      padding-top: 12px;
    }
  }
</style>
