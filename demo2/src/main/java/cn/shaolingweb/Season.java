package cn.shaolingweb;

enum Season {
    SPRING, SUMMER, AUTUMN, WINTER;
    public static Season valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}