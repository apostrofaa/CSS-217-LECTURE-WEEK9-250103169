interface Image {
    void show();
}
class RealImage implements Image {
    String fileName;
    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }
    void loadFromDisk() {
        System.out.println("--> [SLOW] Reading " + fileName + " from hard drive...");
    }
    public void show() {
        System.out.println("Displaying " + fileName + " on screen!");
    }
}
class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;
    private String userRole;
    public ProxyImage(String fileName, String userRole) {
        this.fileName = fileName;
        this.userRole = userRole;
    }
    public void show() {
        if (!"ADMIN".equals(userRole)) {
            System.out.println("[ACCESS DENIED] You lack permission to view: " + fileName);
            return;
        }
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.show();
    }
}

public class Main {
    public static void main(String[] args) {
        Image guestPhoto = new ProxyImage("salary_sheet.png", "GUEST");
        Image adminPhoto = new ProxyImage("salary_sheet.png", "ADMIN");
        guestPhoto.show();
        adminPhoto.show();
    }
}