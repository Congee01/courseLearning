import axios from "axios";
import {VIP_ORDER_MODULE} from "./_prefix";

export const createVipOrder = payload => {
    const {
        userId,
        cost,
        status,
        validTime,
    } = payload;
    return axios
        .post(`${VIP_ORDER_MODULE}/create`, {
            userId,
            cost,
            status,
            validTime,
        })
        .then(res => {
            return res.data;
        });
};

export const payVipOrder = vipOrderId => {
    console.log("vip");
    return axios.post(`${VIP_ORDER_MODULE}/${vipOrderId}`).then(res => res.data);
};