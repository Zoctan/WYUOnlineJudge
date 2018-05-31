<template>
  <div class="home-container">
    <el-carousel :interval="4000">
      <el-carousel-item v-for="item in imageList"
                        :key="item.image"
                        :loading="loadingImage">
        <img class="img" :src="item.image" style="width:100%;">
        <!-- <span class="title">{{ item }}</span> -->
      </el-carousel-item>
    </el-carousel>
    <!--
      <h4>Permission</h4>
      <ol>
        <li v-for="code in permissionCodeList" :key="code">{{ code }}</li>
      </ol> -->

    <el-row>
      <el-col :span="14">
        <div class="title"
             :loading="loadingContest">热门比赛</div>
        <el-card class="box-card">
          <el-table
            :data="contestList"
            style="width: 100%">
            <el-table-column show-overflow-tooltip
                             label="比赛">
              <template slot-scope="scope">
                <router-link v-if="hasPermission('contest:detail')" :to="{name: '比赛详情', params: {id: scope.row.id}}">
                  <span class="hover">{{ scope.row.title }}</span>
                </router-link>
                <span class="hover" v-else @click="noLoginTip">{{ scope.row.title }}</span>
              </template>
            </el-table-column>
            <el-table-column label="开始时间"
                             width="280">
              <template slot-scope="scope">
                {{ unix2CurrentTime(scope.row.startTime) }}
              </template>
            </el-table-column>
          </el-table>
        </el-card>
        <div class="title">赛程</div>
        <schedule/>
      </el-col>

      <el-col :span="9" class="right">
        <div class="title"
             :loading="loadingNote">最新笔记</div>
        <el-card class="box-card">
          <div class="link" v-for="note in noteList">
            <router-link v-if="hasPermission('problem:detail')" :to="{name: '详情', params: {id: note.id}}">
              <span class="hover">{{ note.title }}</span>
            </router-link>
            <span class="hover" v-else @click="noLoginTip">{{ note.title }}</span>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import { unix2CurrentTime } from '@/utils'
  import schedule from './components/schedule'
  import { list as listNote } from '@/api/note'
  import { listHot as listHotContest } from '@/api/contest'
  import { list as listCarousel } from '@/api/carousel'
  import { noLoginTip } from "@/utils/Tip"

  export default {
    name: 'home',
    components: {
      schedule
    },
    created() {
      this.listCarousel()
      this.listNote()
      this.listHotContest()
    },
    data() {
      return {
        imageList: [],
        loadingImage: false,
        noteList: [],
        loadingNote: false,
        contestList: [],
        loadingContest: false
      }
    },
    computed: {
      ...mapGetters(['permissionCodeList'])
    },
    methods: {
      noLoginTip,
      unix2CurrentTime,
      listCarousel() {
        this.loadingImage = true
        listCarousel().then(response => {
          this.imageList = response.data.list
          this.loadingImage = false
        })
      },
      listNote() {
        this.loadingNote = true
        listNote().then(response => {
          this.noteList = response.data.list
          this.loadingNote = false
        })
      },
      listHotContest() {
        this.loadingContest = true
        listHotContest().then(response => {
          this.contestList = response.data.list
          this.loadingContest = false
        })
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .home {
    &-container {
      margin: 30px;
    }
    &-text {
      font-size: 30px;
      line-height: 46px;
    }
  }

  .el-carousel__item .title {
    color: #ffffff;
    background-color: black;
    font-size: 14px;
    opacity: 0.55;
    width: 1000px;
    line-height: 1.5;
    position: absolute;
    bottom: -2px;
    padding-left: 15px;
    margin-bottom: 12px;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
  }

  .title {
    font-size: 22px;
    font-weight: 600;
    margin: 30px 0 15px 0;
  }

  .right {
    margin-left: 30px;
  }
  .link {
    color: blue;
    padding: 10px 0;
    a:hover {
      text-decoration: underline;
    }
  }
</style>
