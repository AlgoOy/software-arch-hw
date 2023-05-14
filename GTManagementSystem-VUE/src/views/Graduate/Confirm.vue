<template>
    <div>
        <el-descriptions title="学生信息"></el-descriptions>
        <div>
            <el-table :data="info" style="width: 100%">
                <el-table-column prop="username" label="姓名" width="200"></el-table-column>
                <el-table-column prop="studentid" label="学号" width="200"></el-table-column>
                <el-table-column prop="department" label="专业" width="200"></el-table-column>
                <!-- <el-table-column prop="topictitle" label="课题名" width="300"></el-table-column> -->

                <el-table-column>
                    <template slot-scope="info" >
                        <el-button type="danger" size="mini" @click="deleteTopic(mytopic.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div  v-show="this.status === 1" style="font-size: 13px; color: #ff0000; text-align: left">注意：审核未通过的课题需要删除后才能重新选择或自拟</div>
        </div>
    </div>

</template>

<script>
    export default {
        name: "InfoConfirm",
        data() {
            return {
                info:[],
                control: 0,
                status:0
            }
        },
        methods: {
            getAllStudents(){
                this.$axios.get("/students/getallstudents").then(res=>{
                    this.info = res.data.data
                })
            },
            check(){
                this.$axios.get(`/students/getallstudent?userid=${this.$store.state.userid}`).then(res=>{
                    if (res.data.data.teacherid === ''){
                        this.control = 0
                    }else {
                        this.getMyTopic()
                    }
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
            this.getAllStudents()
        }
    }
</script>

<style scoped>

</style>