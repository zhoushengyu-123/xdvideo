package net.xdclass.xdvideo.enums;

public enum StateEnum {

    FAIL(-1, "FAIL"),
    SUCCESS(0, "SUCCESS"),
    INNER_ERROR(-1001, "操作失败"),
    EMPTY(-1002, "区域信息为空");


    private int state;

    private String stateInfo;

    private StateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static StateEnum stateOf(int index) {
        for (StateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
