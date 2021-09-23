package webp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LoadLibWebp {

    public static void saveImagesToAnimatedWebp(String[] imageFilePaths, int duration, String targetPath) {

        List<String> cmdList = new ArrayList<>();
        try {
            StringBuilder imageString = new StringBuilder();
            for (String path : imageFilePaths) {
                imageString.append(path).append(" -d ").append(duration).append(" -lossy -q 50 ");
            }

            // 운영체제 구분 (window, window 가 아니면 무조건 linux 로 판단)
            if (System.getProperty("os.name").contains("Windows")) {
                cmdList.add("cmd");
                cmdList.add("/c");
                cmdList.add(".\\img2webp.exe -min_size " + imageString + "-o " + targetPath);
            } else {
                cmdList.add("/bin/sh");
                cmdList.add("-c");
                cmdList.add("./img2webp -min_size " + imageString + "-o " + targetPath);
            }

            for (String str : cmdList) {
                System.out.print(str);
            }

            printResult(cmdList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printResult(List<String> cmdList) throws IOException, InterruptedException {
        Process process;
        Runtime runtime = Runtime.getRuntime();

        BufferedReader successBufferReader;
        BufferedReader errorBufferReader;
        StringBuffer successOutput = new StringBuffer(); // 성공 스트링 버퍼
        StringBuffer errorOutput = new StringBuffer(); // 오류 스트링 버퍼

        String[] target = cmdList.toArray(new String[3]);
        String msg;

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
    }
}
