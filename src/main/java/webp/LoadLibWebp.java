package webp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LoadLibWebp {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        StringBuffer successOutput = new StringBuffer(); // 성공 스트링 버퍼
        StringBuffer errorOutput = new StringBuffer(); // 오류 스트링 버퍼
        BufferedReader successBufferReader = null; // 성공 버퍼
        BufferedReader errorBufferReader = null; // 오류 버퍼
        String msg = null;

        Process process;
        List<String> cmdList = new ArrayList<>();
        try {
//            cmdList.add("")
            cmdList.add("/bin/sh");
            cmdList.add("-c");
//
            cmdList.add("./img2webp ./src/main/resources/images/1.png ./src/main/resources/images/2.png -d 20 -o ./src/main/resources/webps/test2.webp");
            String[] target = cmdList.toArray(new String[cmdList.size()]);
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
