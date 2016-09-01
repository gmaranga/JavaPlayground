package com.javawellgrounded.codingexercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SearchForEdges {

	/**
	 * El instituto geográfico nacional se encarga de, periódicamente, tomar
	 * fotografías aéreas para detectar cambios en el relieve terrestre. Para
	 * agilizar esta tarea desean tener una herramienta que, dada una imagen
	 * tomada, pueda detectar los bordes de las montañas. Las imágenes son
	 * representadas con matrices de números enteros que representan la altura
	 * sobre el nivel del mar en metros en una posición determinada. Un conjunto
	 * de posiciones conexas con la misma altura en la matriz se consideran
	 * estratos. Se considerarán bordes, aquellos estratos que son mínimos
	 * locales, es decir, que ninguno de sus integrantes posee vecinos con
	 * altura menor. Diseñar un algoritmo que, dada una matriz, devuelva otra
	 * matriz con 0 en todas sus posiciones excepto en los bordes de las
	 * montañas que encuentre, donde devuelva 1.
	 * 
	 * 
	 * // Para testear tu código en nuestros servidores debes mantener la
	 * estructura expuesta abajo. // Eres libre de crear nuevas
	 * funciones/procedimientos. // Recuerda que el código que escribas podrá
	 * ser visto por las empresas a las que te postules. // Para validar los
	 * 'vecinos', ten presente las posiciones arriba, abajo, izquierda y
	 * derecha. //Las posiciones diagonales NO deben ser tenidas en cuenta.
	 * class Functions { // int[][] relieve = { {9, 2, 2, 2, 3, 5}, {9, 8, 3, 2,
	 * 4, 5}, {9, 7, 2, 2, 4, 3}, {9, 9, 2, 4, 4, 3}, {9, 2, 3, 4, 3, 5}};
	 * public int[][] encontrar_bordes(int[][] relieve) { // int[][] array = {
	 * {0, 1, 1, 1, 0, 0}, {0, 0, 0, 1, 0, 0}, {0, 0, 1, 1, 0, 1}, {0, 0, 1, 0,
	 * 0, 1}, {0, 1, 0, 0, 1, 0 }}; // return array;
	 * 
	 * } }
	 * 
	 * //La función main() será ejecutada en nuestros servidores llamando a la
	 * expuesta arriba.
	 * 
	 * 
	 * @param args
	 */

	
	class Node {
		
		public Node(int x, int y){
			this.X = x;
			this.Y = y;
		}
		int X;
		int Y;
	}
	
	/**
	 * 9, 2, 2, 2, 3, 5
	   9, 8, 3, 2, 4, 5
       9, 7, 2, 2, 4, 3
       9, 9, 2, 4, 4, 3
       9, 2, 3, 4, 3, 5
	 */
	int[][] relieve = { {9, 2, 2, 2, 3, 5}, {9, 8, 3, 2, 4, 5}, {9, 7, 2, 2, 4, 3}, {9, 9, 2, 4, 4, 3}, {9, 2, 3, 4, 3, 5}};
	
	public int[][] encontrar_bordes(int[][] relieve) {
		
		// int[][] array = { {0, 1, 1, 1, 0, 0}, {0, 0, 0, 1, 0, 0}, {0, 0, 1,
		// 1, 0, 1}, {0, 0, 1, 0, 0, 1}, {0, 1, 0, 0, 1, 0 }};
		// return array;
		
		int[][] edges = new int[relieve.length][relieve[0].length];
		
		printMatrix(relieve);
		
		for(int i = 0; i < relieve.length; i++){
			for(int j = 0; j < relieve[i].length; j++){
				edges[i][j] = 0;
			}
		}
		
		for(int i = 0; i < relieve.length; i++){
			for(int j = 0; j < relieve[i].length; j++){
				Queue<Node> queue = new LinkedList<>();
				
				Node first = new Node(j, i);
				queue.add(first);
				
				while(!queue.isEmpty()){
					
					Node top = queue.remove();
					
					Node[] nb = getNeighbours(top);
					
					for(Node n : nb){
						
						if(isWithinLimits(n, relieve) && edges[n.Y][n.X] != 1){
							
							if(isEdge(n, top, relieve)){
								edges[top.Y][top.X] = 1;
								edges[n.Y][n.X] = 1;
								queue.add(n);
							}
							
						}
					}
					
				}
			}
		}
		
		printMatrix(edges);
		return edges;

	}
	
	private boolean isEdge(Node n, Node top, int[][] relieve) {
		
		boolean estrato = relieve[top.Y][top.X] == relieve[n.Y][n.X];
		boolean localSmallest = false;
		
		if(estrato){
			localSmallest = isLocalSmallest(top, relieve) && isLocalSmallest(n, relieve);
		}
		
		return estrato && localSmallest;
	}

	
	
	private boolean isLocalSmallest(Node node, int[][] relieve) {
		
		Node[] nb = getNeighbours(node);
		
		for (Node n : nb) {
			if(isWithinLimits(n, relieve) && relieve[n.Y][n.X] < relieve[node.Y][node.X]){
				return false;
			}
		}
		return true;
	}

	private boolean isWithinLimits(Node node, int[][] relieve){
		
		int height = relieve.length -1;
		int width = relieve[0].length -1;
		
		return node.Y >= 0 && node.Y <= height && node.X >= 0 && node.X <= width;
	}
	
	public static void main(String[] args) {
		
		SearchForEdges sfe = new SearchForEdges();
		sfe.encontrar_bordes(sfe.relieve);

	}
	
	
	
	private Node[] getNeighbours(Node n){
		List<Node> nb = new ArrayList<>();
		nb.add(new Node(n.X -1, n.Y));
		nb.add(new Node(n.X -1, n.Y -1));
		nb.add(new Node(n.X -1, n.Y + 1));
		nb.add(new Node(n.X + 1, n.Y));
		nb.add(new Node(n.X + 1, n.Y -1));
		nb.add(new Node(n.X + 1, n.Y +1));
		nb.add(new Node(n.X, n.Y -1));
		nb.add( new Node(n.X, n.Y +1));
		
		return nb.toArray(new Node[0]);
	}
	
	//Print 2d array
	private void printMatrix(int[][]matrix){
		
		System.out.println("----------------------------------------------");
		for(int[]row : matrix){
			System.out.println(Arrays.toString(row));
		}
	}
	
	

}
