import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageEncryptionTool {

    // Function to encrypt the image by swapping the red and blue channels
    public static void encryptImage(String inputImagePath, String outputImagePath) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage image = ImageIO.read(inputFile);

        // Iterate over the image pixels and swap red and blue channels
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixel = image.getRGB(x, y);
                
                // Get RGB components
                int red = (pixel >> 16) & 0xFF;
                int green = (pixel >> 8) & 0xFF;
                int blue = pixel & 0xFF;

                // Swap red and blue channels
                int encryptedPixel = (blue << 16) | (green << 8) | red;
                image.setRGB(x, y, encryptedPixel);
            }
        }

        // Save the encrypted image
        File outputFile = new File(outputImagePath);
        ImageIO.write(image, "jpg", outputFile); // You can change the format to PNG or BMP
        System.out.println("Encrypted image saved as " + outputImagePath);
    }

    // Function to decrypt the image (reverse the encryption)
    public static void decryptImage(String inputImagePath, String outputImagePath) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage image = ImageIO.read(inputFile);

        // Iterate over the image pixels and reverse the red and blue channels
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixel = image.getRGB(x, y);

                // Get RGB components
                int red = (pixel >> 16) & 0xFF;
                int green = (pixel >> 8) & 0xFF;
                int blue = pixel & 0xFF;

                // Reverse the swap of red and blue channels
                int decryptedPixel = (blue << 16) | (green << 8) | red;
                image.setRGB(x, y, decryptedPixel);
            }
        }

        // Save the decrypted image
        File outputFile = new File(outputImagePath);
        ImageIO.write(image, "jpg", outputFile);
        System.out.println("Decrypted image saved as " + outputImagePath);
    }

    public static void main(String[] args) {
        try {
            String inputImagePath = "input_image.jpg";  // Path to your input image
            String encryptedImagePath = "encrypted_image.jpg";
            String decryptedImagePath = "decrypted_image.jpg";

            // Encrypt the image
            encryptImage(inputImagePath, encryptedImagePath);

            // Decrypt the image
            decryptImage(encryptedImagePath, decryptedImagePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error processing image.");
        }
    }
}
