import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class HW10_01 {
	public static int count = 0;
	public static int TEST_FILE_NUM = 10 ;

	LinkedList[] edge;
	int numOfV, numOfE;// vertex개수, edge개수
	int[] path;

	public HW10_01(int c) throws IOException {
		//for(int i=10 ; i<=100; i+=10)s
		try {
			Scanner input = new Scanner(new FileReader("input"+c+".txt"));
			String first = input.nextLine();
			StringTokenizer st = new StringTokenizer(first, " ");
			numOfV = Integer.parseInt(st.nextToken());
			numOfE = Integer.parseInt(st.nextToken());

			edge = new LinkedList[numOfV];
			int vertex, edgeV;
			for (int i = 0; i < numOfV; i++)
				edge[i] = new LinkedList();

			for (int i = 0; i < numOfE; i++) {
				String temp = input.nextLine();
				StringTokenizer s = new StringTokenizer(temp, " ");
				vertex = Integer.parseInt(s.nextToken());
				edgeV = Integer.parseInt(s.nextToken());
				// 양방향 추가
				edge[vertex].add(0, edgeV);
				edge[edgeV].add(0, vertex);
			}
			// 그래프 생성 코드작성
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void BFS(int s) {
		Queue<Integer> q = new LinkedList<Integer>();
		int[] color = new int[numOfV];
		// -1은 gray, 0은 white, 1은 black
		path = new int[numOfV];
		// color,path 배열 생성 및 초기화 코드 작성

		q.add(s);
		color[s] = -1;

		for(int i=0; i<path.length; i++)
			path[i] = -1 ;

		while (!q.isEmpty()) {
			int temp = q.remove();

			if (color[temp] != 1) 
			{			
				color[temp] = 1;
				for (int i = 0; i < edge[temp].size(); i++) 
				{
					int visit = (Integer) edge[temp].get(i) ;
					if(color[visit] == 0)
					{
						color[visit] = -1;
						q.add(visit);
						path[visit] = temp ;
					}
				}
			}
		}
	}

	public static void makeArr(int n) throws IOException//n은 숫자갯수
	{
		FileWriter file = null ;
		BufferedWriter fw = null ;
		boolean check = true ;
		int ranCount = 0, ranCount2=0 ;
		int numE =0, numZ=0, numV1=0, numV2=0 ;

		Random edge = new Random();
		numE = edge.nextInt(n*(n-1)/2)+1;

		Random zero = new Random();
		numZ = zero.nextInt(n-1)+1;

		int ranV1[] = new int[numE-1];
		int ranV2[] = new int[numE-1];

		while(ranCount < ranV2.length)
		{
			Random r = new Random();
			numV1 = r.nextInt(n-1);
			ranV1[ranCount] = numV1 ;
			ranCount++;
		}

		while(ranCount2 < ranV2.length)
		{
			Random r2 = new Random();
			numV2 = r2.nextInt(n-1);
			check = true ;
			if(ranV1[ranCount2] == numV2)
				check = false ;

			if(check == true)
			{
				ranV2[ranCount2] = numV2 ;
				ranCount2++;
			}
		}
		file = new FileWriter("input"+count+".txt");
		fw = new BufferedWriter(file);
		fw.write(n+" ");
		fw.write(String.valueOf(numE));
		fw.newLine();
		fw.write(0+" ");
		fw.write(String.valueOf(numZ));
		fw.newLine();
		for(int i=0 ; i<numE-1; i++)
		{
			fw.write(ranV1[i]+" ");
			fw.write(String.valueOf(ranV2[i]));
			fw.newLine();
		}
	//	count++;
		fw.close();
		file.close();
	}

	public void PrintPath(int s, int v) {
		// s : 출발 정점, v : 도착 정점
		if (s == v)
			System.out.println(s);
		else {
			if (path[v] == -1)
			{
				System.out.println("No path from " + s + " to " + v);
				PrintPath(s, v-1);
			}
			else {
				PrintPath(s, path[v]);
				System.out.println(v);
			}
		}
	}
	public static void main(String[] args) throws IOException 
	{
		for(int i=0; i<=10; i++) 
		{
			HW10_01 g = new HW10_01(i);

			g.BFS(0);
			g.PrintPath(0, g.edge.length-1);
			System.out.println("**************************");
		}
	}


}

