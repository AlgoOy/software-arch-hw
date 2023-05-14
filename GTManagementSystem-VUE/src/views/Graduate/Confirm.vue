<template>
    <div>
        <el-descriptions title="学生信息"></el-descriptions>
        <div>
            <el-table :data="infos" style="width: 100%">
                <el-table-column prop="username" label="姓名" width="120"></el-table-column>
                <el-table-column prop="studentid" label="学号" width="120"></el-table-column>
                <el-table-column prop="department" label="院系" width="120"></el-table-column>
                <el-table-column prop="specialty" label="专业" width="120"></el-table-column>
                <el-table-column prop="teacherid" label="指导老师" width="120"></el-table-column>
                <el-table-column prop="topictitle" label="课题名" width="300"></el-table-column>

                <el-table-column>
                    <template slot-scope="infos" >
                        <el-button type="danger" size="mini" @click="infoConfirm(infos.row)">确认</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>

</template>

<script>
    export default {
        name: "InfoConfirm",
        data() {
            return {
                infos:[],
            }
        },
        methods: {
            getInfos(){
                this.$axios.get("/admins/getallinfos").then(res=>{
                    this.infos = res.data.data
                    this.infos = this.infos.filter(info => info.status === 0)
                })
            },
            infoConfirm(info) {
                this.$axios.post('/students/studentready', {studentid: info.studentid}).then(res => {
                    this.getInfos()
                    this.$message.success(res.data.msg)
                })
            },
            deleteTopic(topic) {
                this.$axios.post('/topics/deletetopic', {topictitle: topic.topictitle}).then(res => {
                    this.getMyTopic()
                    this.$message.success(res.data.msg)
                })
            },
        },
        created() {
            this.getInfos()
        }
    }
</script>

<style scoped>

</style>