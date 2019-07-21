package me.zhanshi123.advancedcleaner.item.resist;

public enum SkipState {
    NORMAL(0), WAIT_TO_CONFIRM(1), CONFIRMED(2);

    int state;

    SkipState(int state) {
        this.state = state;
    }

    public static SkipState valueOf(int state) {
        if (state == 2) {
            return SkipState.CONFIRMED;
        } else if (state == 1) {
            return SkipState.WAIT_TO_CONFIRM;
        } else {
            return SkipState.NORMAL;
        }
    }

    public int getState() {
        return state;
    }
}
