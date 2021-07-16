package com.quaint.study.other;

/**
 * mongoId 工具类
 *
 * @author quaint
 * @date 2021/5/20
 */
public abstract class MongoIdUtil {

    /**
     * 开始时间转换为 mongoId
     * @param startTime 秒
     * @return mongoId
     */
    public static String startTimeToMongoId(long startTime) {
        String mongoStr = Long.toHexString(startTime);
        StringBuilder sb = new StringBuilder(mongoStr.toLowerCase());
        while (sb.length() < 24) {
            sb.append("0");
        }
//        return new ObjectId(sb.toString());
        return sb.toString();
    }

    /**
     * 结束时间转换为 mongoId
     * @param endTime 秒
     * @return mongoId
     */
    public static String endTimeToMongoId(long endTime) {
        String mongoStr = Long.toHexString(endTime);
        StringBuilder sb = new StringBuilder(mongoStr.toLowerCase());
        while (sb.length() < 24) {
            sb.append("f");
        }
//        return new ObjectId(sb.toString());
        return sb.toString();
    }

}
