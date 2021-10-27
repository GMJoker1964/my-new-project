import java.io.*;
import java.util.Scanner;

public class DictionaryCommandline {
    DictionaryManagement dictionaryManagement = new DictionaryManagement();
    Scanner input = new Scanner(System.in);

    public void dictionaryAdvance(){
        dictionaryManagement.insertFormFile();
        System.out.println("0. Exit\n1. Show all Words\n2. Edit word\n3. Delete Word\n4. Searcher\n5. Look up\n6. Add new word");
        int chooseFunction = input.nextInt();

        switch (chooseFunction){
            case 0:{
                System.out.println("Chương trình kết thúc.");
                break;
            }

            case 1:{
                showAllWords();
                break;
            }

            case 2:{
                dictionaryManagement.editAnyWord();
                dictionaryExportToFile();
                break;
            }
            case 3:{
                dictionaryManagement.deleteAnyWord();
                dictionaryExportToFile();
                break;
            }
            case 4:{
                dictionarySearcher();
                break;
            }
            case 5:{
                dictionaryManagement.dictionaryLookup();
                break;
            }
            case 6:{
                dictionaryManagement.addWord();
                dictionaryExportToFile();
                break;
            }
            default:{
                System.out.println("KHÔNG HỢP LỆ!!");
            }


        }
    }

    public void showAllWords(){
        if(Dictionary.dictionary.size() == 0) System.out.println("Không có dữ liệu trong file!!");
        else {
            System.out.println("\n---Danh sách gồm " + Dictionary.dictionary.size() + "từ---");

            for(int i = 0; i < Dictionary.dictionary.size(); i++){
                String word_target = (Dictionary.dictionary).get(i).getWord_target();
                String word_explain = (Dictionary.dictionary).get(i).getWord_explain();
                System.out.printf("%-15s %s %n", word_target, word_explain);
            }
        }
    }

    public void dictionarySearcher(){
        System.out.println("\n---Chức năng tìm kiếm bằng nhóm kí tự đầu tiên---");
        System.out.print("Nhập nhóm kí tự bắt đầu: ");
        String stringGroup = input.nextLine();                                          //Insert
        boolean checkExist = false;                                                     //Check word exist
        System.out.println("Các từ bắt đầu bằng " + stringGroup);

        for (Word w : Dictionary.dictionary) {
            if (w.getWord_target().indexOf(stringGroup) == 0) {
                System.out.printf("%-15s %s %n", w.getWord_target(), w.getWord_explain());      //Print word found
                checkExist = true;
            }
        }

        if (!checkExist)
            System.out.println("KHÔNG TÌM THẤY!");

    }

    public void dictionaryExportToFile(){
        BufferedWriter bw = null;

        try{
            FileWriter fw = new FileWriter(dictionaryManagement.fileReader);
            bw = new BufferedWriter(fw);

            for(Word w: Dictionary.dictionary){
                bw.write(w.getWord_target() + "\t" + w.getWord_explain());
                bw.newLine();
            }

            bw.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("\nGHI RA FILE THÀNH CÔNG!");
    }

}

