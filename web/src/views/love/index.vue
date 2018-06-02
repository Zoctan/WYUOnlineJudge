<template>
  <div class="app-container">
    <div class="title">我收藏的题目</div>
    <el-card class="box-card">
          <!-- <el-row>
            <el-col :span="21">
              <el-button @click="doSearch" icon="el-icon-search" circle size="small"/>
              <el-input v-model="searchData" placeholder="搜索题目" style="width:200px"/>
              <el-button v-if="searchData !== null" @click="noSearch" icon="el-icon-close" circle size="small"/>
            </el-col>
            <el-col :span="3">
              <el-button @click="randomStart">随机开始
                <svg-icon icon-class="random"/>
              </el-button>
            </el-col>
          </el-row>
          <el-table :data="problemList"
                    v-loading.body="listLoading"
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
            layout="total, sizes, prev, pager, next, jumper"/> -->
        </el-card>
  </div>
</template>
<script>
export default {
    
}
</script>

<style lang="scss" scoped>
.title{
    padding-bottom: 20px;
    font-size: 18px;
    font-weight: 600;
}
</style>
