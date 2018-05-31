<template>
    <el-card class="box-card">
        <el-table
            :data="tableData"
            style="width: 100%">
            <el-table-column show-overflow-tooltip
                         label="比赛">
        <template slot-scope="scope">
          <!-- <router-link v-if="hasPermission('contest:detail')" :to="{name: '比赛详情', params: {id: scope.row.id}}">
            <span class="hover">{{ scope.row.title }}</span>
          </router-link>
          <span class="hover" v-else @click="noLoginTip">{{ scope.row.title }}</span> -->
        </template>
        </el-table-column>
        <el-table-column label="时间"
                         width="280">
          <template slot-scope="scope">
            <!-- <p>开始时间：{{ unix2CurrentTime(scope.row.startTime) }}</p>
            <p>结束时间：{{ unix2CurrentTime(scope.row.endTime) }}</p> -->
          </template>
        </el-table-column>
        </el-table>
    </el-card>
</template>
<script>
export default {
    
}
</script>
<style lang="scss" scoped>

</style>
