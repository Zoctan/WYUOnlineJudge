<template>
  <div class="app-container">
    <el-table :data="contestList"
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
      <el-table-column label="比赛"
                       align="center">
      <template slot-scope="scope">
        <span v-if="hasPermission('contest:list')" v-text="scope.row.title" @click="dialogFormVisible = true"></span>
        <span v-else v-text="scope.row.title" @click="noLoginTip"></span>
      </template>
      </el-table-column>
      <el-table-column label="开始时间"
                       prop="startTime"
                       sortable
                       align="center">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          {{ unix2CurrentTime(scope.row.startTime) }}
        </template>
      </el-table-column>
      <el-table-column label="结束时间"
                       prop="endTime"
                       sortable
                       align="center">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          {{ unix2CurrentTime(scope.row.endTime) }}
        </template>
      </el-table-column>
      <el-table-column label="状态"
                       prop="status"
                       align="center"
                       :filters="[{text: '未开始', value: 0}, {text: '已开始', value: 1}, {text: '已结束', value: 2}]"
                       :filter-method="filterStatus">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === 0" type="primary">未开始</el-tag>
          <el-tag v-else-if="scope.row.status === 1" type="success">已开始</el-tag>
          <el-tag v-else type="danger">已结束</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="是否允许加入"
                       prop="permitted"
                       align="center"
                       :filters="[{text: '是', value: true}, {text: '否', value: false}]"
                       :filter-method="filterPermitted">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.permitted" type="success">是</el-tag>
          <el-tag v-else type="danger">否</el-tag>
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

    <el-dialog title="join"
               :visible.sync="dialogFormVisible">
      <p>xxxxxxxx</p>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">cancel</el-button>
        <el-button type="primary"
                   :loading="btnLoading"
                   @click.native.prevent="">sure</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import { list as getContestList } from '@/api/contest'
  import { unix2CurrentTime, noLoginTip } from '@/utils'

  export default {
    created() {
      this.getContestList()
    },
    data() {
      return {
        contestList: [], // 比赛列表
        listLoading: false, // 数据加载等待动画
        total: 0, // 数据总数
        listQuery: {
          page: 1, // 页码
          size: 30 // 每页数量
        },
        btnLoading: false, // 按钮等待动画
        dialogFormVisible: false
      }
    },
    methods: {
      unix2CurrentTime,
      noLoginTip,
      filterPermitted(value, row) {
        return row.permitted === value
      },
      filterStatus(value, row) {
        return row.status === value
      },
      getContestList() {
        // 查询列表
        this.listLoading = true
        getContestList(this.listQuery).then(response => {
          this.contestList = response.data.list
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
        this.getContestList()
      },
      handleFilter() {
        // 查询事件
        this.listQuery.page = 1
        this.getContestList()
      },
      getIndex(index) {
        // 表格序号
        return (this.listQuery.page - 1) * this.listQuery.size + index + 1
      }
    }
  }
</script>
