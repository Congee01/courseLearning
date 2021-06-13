<template>
  <div>
    <!-- alert -->
    <v-alert
      class="alert"
      outlined
      type="success"
      text
      v-show="showAlert"
      transition="scroll-y-transition"
    >
      {{ msg }}
    </v-alert>
    <!-- 购买 -->
    <template>
      <v-dialog v-model="dialog" max-width="400">
        <v-card>
          <v-card-title class="headline">
            账户充值
          </v-card-title>
          <v-text-field
            v-model="value"
            label="充值金额"
            class="pa-6"
          ></v-text-field>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn color="green darken-1" text @click="handleRecharge">
              充值
            </v-btn>

            <v-btn color="green darken-1" text @click="dialog = false">
              取消
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </template>

    <!-- 充值会员 -->
    <template>
      <v-dialog v-model="dialog1" max-width="400">
        <v-card>
          <v-card-title class="headline">
            充值会员
          </v-card-title>

          <v-select v-model="chooseTime" :items="intervalTime" item-text="key" label="请选择时间"
          >
          </v-select>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="createVipOrder">
            确定
          </v-btn>
          <v-btn color="green darken-1" text @click="dialog1 = false">
            取消
          </v-btn>
        </v-card>
      </v-dialog>
    </template>
    <template>
      <v-dialog v-model="dialog2" max-width="400">
        <v-card>
          <v-card-title class="headline">
            确认支付
          </v-card-title>
          <v-card-text v-if="chooseTime ==='1分钟'"> 是否花费{{ cost[0] }}元成为{{this.chooseTime}}会员？ </v-card-text>
          <v-card-text v-if="chooseTime ==='1小时'"> 是否花费{{ cost[1] }}元成为{{this.chooseTime}}会员？ </v-card-text>
          <v-card-text v-if="chooseTime ==='1天'"> 是否花费{{ cost[2] }}元成为{{this.chooseTime}}会员？ </v-card-text>
          <v-card-text v-if="chooseTime ==='1周'"> 是否花费{{ cost[3] }}元成为{{this.chooseTime}}会员？ </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="green darken-1" text @click="handleBeVip">
              确定
            </v-btn>

            <v-btn color="green darken-1" text @click="dialog2 = false">
              取消
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </template>
    <v-container class="pl-16 pr-16">
      <form class="pa-12 grey lighten-5 mt-8">
        <v-text-field v-model="userInfo.id" label="ID" readonly></v-text-field>
        <v-text-field
          v-model="userInfo.uname"
          label="用户名"
          readonly
        ></v-text-field>
        <v-text-field
          v-model="userInfo.phone"
          label="手机号"
          readonly
        ></v-text-field>
        <v-text-field
          v-model="userInfo.userRole"
          label="用户角色"
          readonly
        ></v-text-field>
        <v-text-field
          v-model="userInfo.createTime"
          label="注册时间"
          readonly
        ></v-text-field>
        <v-text-field
            v-model="userInfo.isVip"
            label="是否是会员"
            readonly

        >
        </v-text-field>

        <v-text-field
          v-model="userInfo.balance"
          label="账户余额"
          readonly
        ></v-text-field>
        <v-btn color="primary" dark @click.stop="dialog = true">
          充值
        </v-btn>
        <p></p><v-btn  v-if="!userInfo.isVip" color="primary" dark @click="show">
          点击此处成为会员
        </v-btn>
      </form>
    </v-container>
  </div>
</template>

<script>
import { getUser } from "@/api/user";
import { recharge } from "@/api/recharge";
import {createVipOrder} from "@/api/VipOrder";
import {payVipOrder} from "@/api/VipOrder";

export default {
  name: "UserCenter",

  data() {
    return {
      userInfo: {
        id: 0,
        uname: "",
        phone: "",
        password: null,
        picture: null,
        balance: 0,
        userRole: "",
        createTime: ""
      },
      dialog: false,
      dialog1: false,
      dialog2: false,
      dialog3:true,
      showAlert: false,
      value: 0,
      msg: "",
      cost:[1,10,100,1000],
      placeholder:"请选择时间",
      trueCost:"",
      currentOrderId:"",
      chooseTime:{key:'1分钟',costId:0},
      chooseId:"",
      intervalTime:[
        {
          key:'1周',
          costId:3
        },
        {
          key:'1天',
          costId:2
        },
        {
          key:'1小时',
          costId:1
        },
        {
          key:'1分钟',
          costId:0
        }
      ]
    };
  },

  methods: {
    handleRecharge() {
      recharge({ userId: this.userInfo.id, value: this.value }).then(res => {
        console.log(res);
        if (res && res.code === 1) {
          this.msg = res.msg;
          this.dialog = false;
          this.showAlert = true;
          this.refreshUserInfo();
          setTimeout(() => {
            this.showAlert = false;
          }, 1000);
        }
      });
    },
    show(){
      this.dialog1=true;
    },
    createVipOrder() {
      console.log(this.chooseTime);
      this.dialog2=true;
      const userId = window.localStorage.getItem("userId");
      if(this.chooseTime ==="1分钟"){
        this.trueCost=this.cost[0];
      }
      else if(this.chooseTime ==="1小时"){
        this.trueCost=this.cost[1];
      }
      else if(this.chooseTime ==="1天"){
        this.trueCost=this.cost[2];
      }
      else{
        this.trueCost=this.cost[3];
      }
      console.log(this.trueCost);
      const payload = {
        userId: userId,
        cost: this.trueCost,
        validTime : this.chooseTime,
        status: 3,
      };
      createVipOrder(payload).then(res => {
        console.log(res);
        this.currentOrderId=res.data.orderId;
        if (res.code === 1) {
          this.showSuccessDialog = true;
          setTimeout(() => {
            this.showSuccessDialog = false;
          }, 1000);
        } else {
          this.showFailDialog = true;
          this.msg = res.msg;
          setTimeout(() => {
            this.showFailDialog = false;
          }, 1000);
        }
      });
    },

    handleBeVip(){
      console.log(this.currentOrderId);
      payVipOrder(this.currentOrderId).then(res => {
        this.dialog2=false;
        this.dialog1=false;
        this.snackBarColor = "success";
        this.snackBarMsg = res.msg;
        this.showSnackBar = true;
        this.refreshUserInfo();
      });
    },
    refreshUserInfo() {
      const userId = window.localStorage.getItem("userId");
      getUser(userId).then(res => {
        this.userInfo = res || {};
        console.log(res);
      });
    }
  },

  mounted() {
    this.refreshUserInfo();
  }
};
</script>

<style scoped>
.alert {
  position: fixed;
  left: 50%;
  top: 100px;
  z-index: 999;
}


</style>
