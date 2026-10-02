package servermonitor;

/** Quy ước chung giữa Client và Agent Server. Cả nhóm dùng chung, không tự ý sửa. */
public final class Protocol {
    public static final int DEFAULT_PORT = 6000; // Agent dùng cùng số cổng cho TCP và UDP
    public static final String CMD_GET = "GET";   // Client gửi để xin số liệu
    public static final String CMD_QUIT = "QUIT"; // Client gửi để ngắt kết nối TCP

    private Protocol() { }
}