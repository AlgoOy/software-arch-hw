<template>
  <div>
    <el-descriptions title="学生信息"></el-descriptions>
    <div>
      <el-table :data="infos" style="width: 100%">
        <el-table-column
          prop="topictitle"
          label="课题名"
          width="200"
        ></el-table-column>
        <el-table-column
          prop="topicdepict"
          label="课题描述"
          width="300"
        ></el-table-column>
        <!-- <el-table-column
          prop="paperid"
          label="paperid"
          width="300"
        ></el-table-column> -->
        <el-table-column label="操作" width="300">
          <template slot-scope="paper">
            <el-button
              type="success"
              size="mini"
              @click="downloadPaper(paper.row)"
              >下载</el-button
            >
          </template>
        </el-table-column>

        <el-table-column label="盲审成绩" width="300">
          <template slot-scope="infos">
            <input
              type="text"
              v-model="infos.row.blindscore_temp"
              placeholder="请输入成绩"
              @change="change()"
            />
          </template>
        </el-table-column>

        <el-table-column>
          <template slot-scope="infos">
            <el-button type="danger" size="mini" @click="infoBlind(infos.row)"
              >提交</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "Infoblind",
  data() {
    return {
      blindscore: "",
      infos: [],
    };
  },
  methods: {
    change() {
      this.$forceUpdate(); //强制刷新
    },
    getInfos() {
      this.$axios.get("/admins/getallinfos").then((res) => {
        this.infos = res.data.data;
        this.infos = this.infos.filter((info) => info.status === 1);
      });
    },
    infoBlind(info) {
      this.$axios
        .post("/students/blindready", {
          topictitle: info.topictitle,
          blindscore: info.blindscore_temp,
        })
        .then((res) => {
          this.getInfos();
          this.$message.success(res.data.msg);
        });
    },
    downloadPaper(paper) {
      this.$axios
        .get(`/papers/downloadpaper?paperid=${paper.paperid}`, {
          responseType: "blob",
        })
        .then((res) => {
          let url = window.URL.createObjectURL(new Blob([res.data]));
          let a = document.createElement("a");
          a.setAttribute("download", paper.filename);
          a.href = url;
          a.click();
        });
    },
  },

  created() {
    this.getInfos();
  },
};
</script>
