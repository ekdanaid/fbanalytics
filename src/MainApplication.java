import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import model.LikesModel;
import model.PagesModel;
import model.UsersModel;

import java.io.FileInputStream;

public class MainApplication {

	public <T> List<T> intersection(List<T> list1, List<T> list2) {
		List<T> list = new ArrayList<T>();

		for (T t : list1) {
			if (list2.contains(t)) {
				list.add(t);
			}
		}

		return list;
	}

	public static void main(String[] args) {
		ArrayList<UsersModel> userList = new ArrayList<UsersModel>();
		ArrayList<PagesModel> pageList = new ArrayList<PagesModel>();
		ArrayList<LikesModel> likeList = new ArrayList<LikesModel>();
		String cs = "UTF-8";
		String path = "/Users/ekdanaid/fbanalysis/resource/";
		String likeFile = path + "likes_example.txt";
		String pageFile = path + "pages_example.txt";
		String userFile = path + "users_example.txt";
		int maleCount = 0;
		int femaleCount = 0;
		try {

			// UserFile
			FileInputStream fis = new FileInputStream(userFile);
			InputStreamReader isr = new InputStreamReader(fis, cs);
			BufferedReader br = new BufferedReader(isr);
			String line;
			Scanner scanner = new Scanner(System.in);
			while ((line = br.readLine()) != null) {
				String[] strArr = line.split(",");
				userList.add(new UsersModel(strArr[0], strArr[1], strArr[2], strArr[3]));
			}
			br.close();

			// PageFile
			fis = new FileInputStream(pageFile);
			isr = new InputStreamReader(fis, cs);
			br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				String[] strArr = line.split(",");
				pageList.add(new PagesModel(strArr[0], strArr[1]));
			}
			br.close();

			// LikeFile
			fis = new FileInputStream(likeFile);
			isr = new InputStreamReader(fis, cs);
			br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				String[] ColonArr = line.split(":");
				likeList.add(new LikesModel(ColonArr[0], new ArrayList<String>(Arrays.asList(ColonArr[1].split(",")))));
			}
			br.close();

			System.out.println("\nFacebook Fanpage Analytics");
			System.out.println("==================");
			System.out.println("1. View a user profile");
			System.out.println("2. List all pages liked by a user");
			System.out.println("3. List all fans of a page");
			System.out.println("4. List gender profile of a page");
			System.out.println("5. List all mutual fans of two pages");
			System.out.print(">>> Enter an option: ");
			String option = scanner.next();
			switch (option) {
			case "1":
				System.out.print("Enter a username : ");
				String username = scanner.next();
				UsersModel uResult = userList.stream().filter(x -> username.equals(x.getName())).findAny().orElse(null);
				if (uResult != null) {
					System.out.println("*** " + uResult.toString());
				} else {
					System.out.println("Wrong Input");
				}
				break;
			case "2":
				System.out.print("Enter a username : ");
				username = scanner.next();
				uResult = userList.stream().filter(x -> username.equals(x.getName())).findAny().orElse(null);
				if (uResult != null) {
					String userID = uResult.getUserID();
					for (int i = 0; i < likeList.size(); i++) {
						if (likeList.get(i).getUserID().contains(userID)) {
							String pageID = likeList.get(i).getPageID();
							PagesModel pResult = pageList.stream().filter(x -> pageID.equals(x.getPageID())).findAny()
									.orElse(null);
							System.out.print(pResult.getName() + " ");
						}
						;
					}
				} else {
					System.out.println("Wrong Input");
				}
				break;
			case "3":
				System.out.print(">>> Enter a page name: ");
				String page = scanner.next();
				PagesModel pResult = pageList.stream().filter(x -> page.equals(x.getName())).findAny().orElse(null);
				if (pResult != null) {
					LikesModel lResult = likeList.stream().filter(x -> pResult.getPageID().equals(x.getPageID()))
							.findAny().orElse(null);
					for (int i = 0; i < lResult.getUserID().size(); i++) {
						String userID = lResult.getUserID().get(i);
						uResult = userList.stream().filter(x -> userID.equals(x.getUserID())).findAny().orElse(null);
						System.out.print(uResult.getName() + " ");
					}
				} else {
					System.out.println("Wrong Input");
				}
				break;
			case "4":
				System.out.print(">>> Enter a page name: ");
				page = scanner.next();
				pResult = pageList.stream().filter(x -> page.equals(x.getName())).findAny().orElse(null);
				if (pResult != null) {
					LikesModel lResult = likeList.stream().filter(x -> pResult.getPageID().equals(x.getPageID()))
							.findAny().orElse(null);
					for (int i = 0; i < lResult.getUserID().size(); i++) {
						String userID = lResult.getUserID().get(i);
						uResult = userList.stream().filter(x -> userID.equals(x.getUserID())).findAny().orElse(null);
						if ("male".equals(uResult.getGender())) {
							maleCount++;
						} else if ("female".equals(uResult.getGender())) {
							femaleCount++;
						}
						;
					}
					System.out.println("*** Result: Male= " + maleCount + ", Female= " + femaleCount);
				} else {
					System.out.println("Wrong Input");
				}
				break;
			case "5":
				System.out.print(">>> Enter two pages: ");
				String page1 = scanner.next();
				String page2 = scanner.next();
				PagesModel pResult1 = pageList.stream().filter(x -> page1.equals(x.getName())).findAny().orElse(null);
				PagesModel pResult2 = pageList.stream().filter(x -> page2.equals(x.getName())).findAny().orElse(null);
				if (pResult1 != null && pResult2 != null) {
					LikesModel lResult1 = likeList.stream().filter(x -> pResult1.getPageID().equals(x.getPageID()))
							.findAny().orElse(null);
					LikesModel lResult2 = likeList.stream().filter(x -> pResult2.getPageID().equals(x.getPageID()))
							.findAny().orElse(null);
					List<String> mutualList = new MainApplication().intersection(lResult1.getUserID(),
							lResult2.getUserID());
					for (int i = 0; i < mutualList.size(); i++) {
						String mutualListID = mutualList.get(i);
						uResult = userList.stream().filter(x -> mutualListID.equals(x.getUserID())).findAny()
								.orElse(null);
						System.out.print(uResult.getName() + " ");
					}
				} else {
					System.out.println("Wrong Input");
				}
				break;
			default:
				System.out.println("Wrong Input");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
