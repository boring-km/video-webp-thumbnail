package webp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LoadLibWebp {

    public static void saveImagesToAnimatedWebp(String[] imageFilePaths, int duration, String targetPath) {
        Runtime runtime = Runtime.getRuntime();
        StringBuffer successOutput = new StringBuffer(); // 성공 스트링 버퍼
        StringBuffer errorOutput = new StringBuffer(); // 오류 스트링 버퍼
        BufferedReader successBufferReader; // 성공 버퍼
        BufferedReader errorBufferReader; // 오류 버퍼
        String msg;

        Process process;
        List<String> cmdList = new ArrayList<>();
        try {
            cmdList.add("/bin/sh");
            cmdList.add("-c");
            StringBuilder imageString = new StringBuilder();
            for (String path : imageFilePaths) {
                imageString.append(path).append(" ");
            }
            cmdList.add("./img2webp " + imageString + "-d " + duration + " -o " + targetPath);
            String[] target = cmdList.toArray(new String[3]);
            process = runtime.exec(target);

            // shell 실행이 정상 동작했을 경우
            successBufferReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "EUC-KR"));

            while ((msg = successBufferReader.readLine()) != null) {
                successOutput.append(msg).append(System.getProperty("line.separator"));
            }

            // shell 실행시 에러가 발생했을 경우
            errorBufferReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "EUC-KR"));
            while ((msg = errorBufferReader.readLine()) != null) {
                errorOutput.append(msg).append(System.getProperty("line.separator"));
            }

            // 프로세스의 수행이 끝날때까지 대기
            process.waitFor();

            // shell 실행이 정상 종료되었을 경우
            if (process.exitValue() == 0) {
                System.out.println("성공");
                System.out.println(successOutput);
                System.out.println(errorOutput);
            } else {
                // shell 실행이 비정상 종료되었을 경우
                System.out.println("비정상 종료");
                System.out.println(errorOutput);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
