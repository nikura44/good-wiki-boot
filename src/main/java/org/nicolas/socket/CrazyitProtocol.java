package org.nicolas.socket;

/**
 * @author zorth
 */
public interface CrazyitProtocol {
    /**
     * defined length of protocol string
     */
    int PROTOCOL_LENGTH = 2;
    /**
     * All messages exchanged between the server and client should be preceded and followed by this string
     */
    String MSG_ROUND = "△▽";
    String USER_ROUND = "□☆";
    String LOGIN_SUCCESS = "☆▷";
    String NAME_REP = "-1";
    String PRIVATE_ROUND = "◆★";
    String SPLIT_SIGN = "☀";
}

