package servermonitor;

/**
 * Chỉ số sức khỏe của 1 Agent Server.
 * Gửi qua mạng dạng 1 dòng: METRICS|name=web01|cpu=23.5|memUsed=4096|memTotal=8192|conn=57
 */
public class Metrics {
    public static final String PREFIX = "METRICS";

    public String name = "";   // tên agent
    public double cpu;         // CPU load (%)
    public long memUsedMB;     // bộ nhớ đã dùng (MB)
    public long memTotalMB;    // tổng bộ nhớ (MB)
    public int connections;    // số kết nối TCP đang mở
    public long timestamp;     // thời điểm Client nhận (Client tự điền)

    public double memPercent() {
        return memTotalMB == 0 ? 0 : memUsedMB * 100.0 / memTotalMB;
    }

    /** Đóng gói thành chuỗi để gửi. */
    public String toLine() {
        return PREFIX + "|name=" + name.replace("|", " ").replace("=", " ")
                + "|cpu=" + Math.round(cpu * 10) / 10.0
                + "|memUsed=" + memUsedMB
                + "|memTotal=" + memTotalMB
                + "|conn=" + connections;
    }

    /** Đọc chuỗi nhận được thành đối tượng. Sai định dạng thì ném IllegalArgumentException. */
    public static Metrics parse(String line) {
        if (line == null || !line.startsWith(PREFIX + "|")) {
            throw new IllegalArgumentException("Sai định dạng: " + line);
        }
        Metrics m = new Metrics();
        try {
            for (String part : line.trim().split("\\|")) {
                int eq = part.indexOf('=');
                if (eq < 0) continue;
                String key = part.substring(0, eq), val = part.substring(eq + 1);
                switch (key) {
                    case "name":     m.name = val; break;
                    case "cpu":      m.cpu = Double.parseDouble(val); break;
                    case "memUsed":  m.memUsedMB = Long.parseLong(val); break;
                    case "memTotal": m.memTotalMB = Long.parseLong(val); break;
                    case "conn":     m.connections = Integer.parseInt(val); break;
                    default: break; // trường lạ thì bỏ qua
                }
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Số không hợp lệ: " + line);
        }
        return m;
    }
}