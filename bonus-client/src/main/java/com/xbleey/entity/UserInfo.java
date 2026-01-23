/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: UserInfo
 * Author:   11580
 * Date:     2019/6/17 0017 14:07
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/6/17 0017
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private String userName;
    private String passWord;
    private String[] role;
}
 

