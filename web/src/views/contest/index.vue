<template>
  <div class="app-container">
    <el-card class="box-card">
      <el-row>
        <el-col :span="21">
          <el-button @click="doSearch" icon="el-icon-search" circle size="small" />
          <el-input v-model="searchData" placeholder="搜索比赛" style="width:200px" />
          <el-button v-if="searchData !== null" @click="noSearch" icon="el-icon-close" circle size="small" />
        </el-col>
        <el-col :span="3">
          <Clock ref="clock" />
        </el-col>
      </el-row>
      <el-table tooltip-effect="dark"
                :data="contestList"
                v-loading.body="listLoading">
        <el-table-column label="#"
                         align="center"
                         width="50">
          <template slot-scope="scope">
            <span>{{ getIndex(scope.$index) }}</span>
          </template>
        </el-table-column>
        <el-table-column show-overflow-tooltip
                         label="比赛">
        <template slot-scope="scope">
          <router-link v-if="hasPermission('contest:detail')" :to="{name: '比赛详情', params: {id: scope.row.id}}">
            <span class="hover">{{ scope.row.title }}</span>
          </router-link>
          <span class="hover" v-else @click="noLoginTip">{{ scope.row.title }}</span>
        </template>
        </el-table-column>
        <el-table-column label="时间"
                         align="center"
                         width="280">
          <template slot-scope="scope">
            <p>开始时间：{{ unix2CurrentTime(scope.row.startTime) }}</p>
            <p>结束时间：{{ unix2CurrentTime(scope.row.endTime) }}</p>
          </template>
        </el-table-column>
        <el-table-column label="时长"
                         sortable
                         align="center"
                         width="120">
          <template slot-scope="scope">
            {{ unixDifference(scope.row.startTime, scope.row.endTime) }}
          </template>
        </el-table-column>
        <el-table-column label="人数"
                         sortable
                         align="center"
                         width="120">
          <template slot-scope="scope">
            {{ scope.row.joinCount }}
          </template>
        </el-table-column>
        <el-table-column label="状态"
                         prop="status"
                         align="center"
                         width="120"
                         :filters="[{text: '未开始', value: 0}, {text: '已开始', value: 1}, {text: '已结束', value: 2}]"
                         :filter-method="filterStatus">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status === 0" type="primary">未开始</el-tag>
            <el-tag v-else-if="scope.row.status === 1" type="success">已开始</el-tag>
            <el-tag v-else type="danger">已结束</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="允许加入"
                         prop="permitted"
                         align="center"
                         width="120"
                         :filters="[{text: '是', value: true}, {text: '否', value: false}]"
                         :filter-method="filterPermitted">
          <template slot-scope="scope">
            <i v-if="scope.row.permitted" class="el-icon-check" style="color: #4CAF50"></i>
            <i v-else class="el-icon-close" style="color: #F44336"></i>
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
    </el-card>
  </div>
</template>
<script>
  import { list as getContestList } from '@/api/contest'
  import { unix2CurrentTime, unixDifference } from '@/utils'
  import { noLoginTip } from '@/utils/Tip'
  import Clock from '@/components/Clock'

  export default {
    components: { Clock },
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
        searchData: null, // 搜索内容
        search: {
          isSearch: false, // 是否进行过搜索
          beforeSearchList: [] // 搜索前的数据
        },
        time: null
      }
    },
    methods: {
      unix2CurrentTime,
      unixDifference,
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
      },

      noSearch() {
        this.searchData = null
        if (this.search.isSearch) {
          this.contestList = this.search.beforeSearchList
          this.search.beforeSearchList = []
          this.search.isSearch = false
        }
      },
      doSearch() {
        if (this.searchData == null) {
          this.Tip.defaultError('搜索内容不能为空哦！')
        } else {
          const resultList = []
          this.contestList.forEach(i => {
            if (i.title.indexOf(this.searchData) !== -1) {
              resultList.push(i)
            }
          })
          if (resultList.length === 0) {
            this.Tip.defaultWarning('找不到相关内容哦！')
          } else {
            this.search.beforeSearchList = this.contestList
            this.search.isSearch = true
            this.contestList = resultList
          }
        }
      }
    }
  }
</script>
