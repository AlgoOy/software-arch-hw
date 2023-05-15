<template>
  <div>
    <el-descriptions title="学生信息"></el-descriptions>
    <div>
      <el-table :data="infos" style="width: 100%">
        <el-table-column
          prop="studentid"
          label="学号"
          width="120"
        ></el-table-column>
        <el-table-column
          prop="username"
          label="姓名"
          width="120"
        ></el-table-column>
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
        <el-table-column label="答辩成绩" width="300">
          <template slot-scope="infos">
            <input
              type="text"
              v-model="infos.row.defensescore_temp"
              placeholder="请输入成绩"
              @change="change()"
            />
          </template>
        </el-table-column>

        <el-table-column>
          <template slot-scope="infos">
            <el-button type="danger" size="mini" @click="infoDefense(infos.row)">提交</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "Infodefense",
  data() {
    return {
      defensescore: "",
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
        this.infos = this.infos.filter((info) => info.status === 2);
        console.log(this.infos);
      });
    },
    infoDefense(info) {
      this.$axios
        .post("/students/defenseready", {
          studentid: info.studentid,
          defensescore: info.defensescore_temp,
        })
        .then((res) => {
          this.getInfos();
          this.$message.success(res.data.msg);
        });
    },
  },

  created() {
    this.getInfos();
  },
};
</script>
