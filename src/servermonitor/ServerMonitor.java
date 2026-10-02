/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package servermonitor;

/**
 *
 * @author Hp
 */
public class ServerMonitor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Metrics m = new Metrics();
m.name = "test";
m.cpu = 23.5;
m.memUsedMB = 4096;
m.memTotalMB = 8192;
m.connections = 57;

String line = m.toLine();
System.out.println("Gửi : " + line);

Metrics m2 = Metrics.parse(line);
System.out.println("Nhận: " + m2.name + " | CPU " + m2.cpu + "% | Mem "
        + m2.memPercent() + "% | Kết nối " + m2.connections);
    }
    
}
