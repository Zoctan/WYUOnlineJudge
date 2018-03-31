<template>
  <div class="app-container">
    <el-input v-model="searchData" placeholder="搜索题目" style="width:240px" />
    <el-button type="primary" @click="doSearch">搜索</el-button>
    <el-table :data="problemList"
              v-loading.body="listLoading"
              element-loading-text="loading"
              fit>
      <el-table-column label="#"
                       align="center"
                       width="50">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"></span>
        </template>
      </el-table-column>
      <el-table-column label="题目"
                       align="center"
                       prop="title" />
      <el-table-column label="标签"
                       prop="tag"
                       align="center"
                       :filters="tags"
                       :filter-method="filterTag">
        <template slot-scope="scope">
          {{ scope.row.tags.split(' ').join(' / ') }}
        </template>
      </el-table-column>
      <el-table-column label="通过率"
                       prop="accepted/submitted"
                       sortable
                       align="center">
        <template slot-scope="scope">
          {{ (scope.row.accepted/scope.row.submitted).toFixed(2) * 100 + '%' }}
        </template>
      </el-table-column>
      <el-table-column label="难度"
                       sortable
                       prop="level"
                       align="center"
                       :filters="[{text: '简单', value: 1}, {text: '中等', value: 2}, {text: '困难', value: 3}]"
                       :filter-method="filterLevel">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.level === 1" type="success">简单</el-tag>
          <el-tag v-else-if="scope.row.level === 2" type="warning">中等</el-tag>
          <el-tag v-else type="danger">困难</el-tag>
        </template>
      </el-table-column>
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
  </div>
</template>
<script>
  import { list as getProblemList } from '@/api/problem'

  export default {
    created() {
      this.getProblemList()
    },
    data() {
      return {
        problemList: [], // 题目列表
        tags: [], // 标签列表
        listLoading: false, // 数据加载等待动画
        total: 0, // 数据总数
        listQuery: {
          page: 1, // 页码
          size: 30 // 每页数量
        },
        searchData: null,
        btnLoading: false // 按钮等待动画
      }
    },
    methods: {
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
        const set = new Set()
        this.problemList.forEach((problem) => {
          const tagList = problem.tags.split(' ')
          tagList.forEach(tag => set.add(tag))
        })
        set.forEach(tag => this.tags.push({ text: tag, value: tag }))
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
      doSearch() {
        if (this.searchData == null) {
          this.$message({
            message: '搜索内容不能为空哦',
            type: 'danger'
          })
        } else {
          const resultList = []
          this.problemList.forEach(problem => {
            if (problem.title.indexOf(this.searchData) !== -1) {
              resultList.push(problem)
            }
          })
          if (resultList.length === 0) {
            this.$message({
              message: '找不到相关题目哦',
              type: 'warning'
            })
          } else {
            this.problemList = resultList
          }
        }
      }
    }
  }
</script>
