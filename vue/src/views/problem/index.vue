<template>
  <div class="app-container">
    <el-table :data="problemList"
              v-loading.body="listLoading"
              element-loading-text="loading"
              border fit highlight-current-row>
      <el-table-column label="#"
                       align="center"
                       width="80">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"></span>
        </template>
      </el-table-column>
      <el-table-column label="题目"
                       align="center"
                       prop="title" />
      <el-table-column label="标签"
                       align="center">
        <template slot-scope="scope">
          <span v-for="item in scope.row.tags.split(' ')">
            <el-tag type="success">{{ item }}</el-tag>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="难度"
                       align="center">
        <template slot-scope="scope">
          {{ scope.row.level + "级" }}
        </template>
      </el-table-column>
      <el-table-column label="通过率"
                       align="center">
        <template slot-scope="scope">
          {{ scope.row.accepted/scope.row.submitted }}
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
        listLoading: false, // 数据加载等待动画
        total: 0, // 数据总数
        listQuery: {
          page: 1, // 页码
          size: 30 // 每页数量
        },
        btnLoading: false // 按钮等待动画
      }
    },
    methods: {
      getProblemList() {
        // 查询列表
        this.listLoading = true
        getProblemList(this.listQuery).then(response => {
          this.problemList = response.data.list
          this.total = response.data.total
          this.listLoading = false
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
      }
    }
  }
</script>
