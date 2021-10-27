import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class DictionaryManagement {

    public final String fileReader = "src/dictionaries.txt";
    private Scanner input = new Scanner(System.in);

    public void insertFormCommandLine(){
        System.out.print("Nhập số lượng từ cần insert: ");
        int numberOfWords = input.nextInt();
        input.nextLine();

        for(int i = 0; i < numberOfWords; i++){
            Word newWord = new Word();
            System.out.print("Từ " + (i + 1) + ": ");
            newWord.setWord_target(input.nextLine());

            System.out.print("Dịch nghĩa " + (i+1) + ": ");
            newWord.setWord_explain(input.nextLine());

            Dictionary.dictionary.add(newWord);
        }
        System.out.println("Nhập thành công!");
    }

    public void insertFormFile(){
        BufferedReader br = null;

        try{
            FileReader fr = new FileReader(fileReader);
            br = new BufferedReader(fr);

            String contentLine = br.readLine();
            while(contentLine != null){
                if(contentLine.indexOf("\t") == -1){
                    contentLine = br.readLine();
                    continue;
                }

                Word newWord = new Word(contentLine);
                (Dictionary.dictionary).add(newWord);
                contentLine = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n---ĐỌC TỪ FILE THÀNH CÔNG!");
    }

    public void dictionaryLookup(){
        System.out.println("\n---Chức năng tìm kiếm từ---");
        System.out.print("Nhập từ cần tìm kiếm: ");
        String wordLookup = input.nextLine();

        for(Word w: Dictionary.dictionary){
            if(wordLookup.equals(w.getWord_target())){
                System.out.println("Từ đang tìm: " + wordLookup);
                System.out.println("Dịch nghĩa: " + w.getWord_explain());
                return;
            }
        }
        System.out.println("KHÔNG TÌM THẤY!");
    }

    public void editAnyWord(){
        System.out.println("\n---Chức năng sửa từ---");
        System.out.print("Nhập từ bạn muốn sửa: ");
        String needEditing = input.nextLine();                                          //Insert word need editing

        for (int i = 0; i < Dictionary.dictionary.size(); i ++) {
            if(Dictionary.dictionary.get(i).getWord_target(). equals(needEditing)) {     //If find word
                System.out.println("Đã tìm thấy!");
                System.out.print("Nhập từ mới: ");
                String new_target = input.nextLine();                                   //Insert target

                System.out.print("Nhập dịch nghĩa: ");
                String new_explain = input.nextLine();                                  //Insert explain
                Dictionary.dictionary.set(i, new Word(new_target, new_explain));        //Replace by new word

                System.out.println("SỬA THÀNH CÔNG!");
                return;
            }
        }
        System.out.println("KHÔNG TÌM THẤY!");
    }

    public void deleteAnyWord(){
        System.out.println("\n---Chức năng xóa từ---");
        System.out.print("Chọn từ mà bạn muốn xóa");
        String needDelete = input.nextLine();

        for(int i = 0; i < Dictionary.dictionary.size(); i++){
            if(Dictionary.dictionary.get(i).getWord_target().equals(needDelete)){
                System.out.println("Đã tìm thấy!");
                Dictionary.dictionary.remove(i);
                System.out.println("XÓA THÀNH CÔNG!");
                return;
            }
        }
        System.out.println("KHÔNG TÌM THẤY!");
    }

    public void addWord(){
        System.out.println("\n---Chức năng thêm từ---");
        System.out.print("Nhập từ bạn muốn thêm: ");
        String newWord = input.nextLine();
        System.out.print("Nhập nghĩa: ");
        String mean = input.nextLine();

        Dictionary.dictionary.add(new Word(newWord, mean));
        System.out.println("Thêm thành công!");
    }

}
