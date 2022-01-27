package br.com.generation.tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Tabuleiro {
	Scanner read = new Scanner(System.in);
	Random random = new Random();
	public int[][] velha = new int[3][3];

	public void MontarTabuleiro() {
		System.out.println("========== INÍCIO ==========");
		System.out.println("USER = X\n" + "PC = O\n");

		for (int l = 0, i = 1; l < velha.length; l++) {
			for (int c = 0; c < velha[l].length; c++) {
				velha[l][c] = i++;

				if (c == 2) {
					System.out.print(velha[l][c]);
				} else {
					System.out.print(velha[l][c] + " | ");
				}
			}
			if (l != 2) {
				System.out.print("\n----------\n");

			}
		}
		System.out.println();
		jogar();
	}

	public void apresentarTabuleiro() {
		for (int l = 0; l < velha.length; l++) {
			for (int c = 0; c < velha[l].length; c++) {

				if (c == 2) {
					if (velha[l][c] == 10) {
						System.out.print("X");
					} else if (velha[l][c] == -10) {
						System.out.print("O");
					} else {
						System.out.print(velha[l][c]);
					}
				} else {
					if (velha[l][c] == 10) {
						System.out.print("X" + " | ");
					} else if (velha[l][c] == -10) {
						System.out.print("O" + " | ");
					} else {
						System.out.print(velha[l][c] + " | ");
					}
				}
			}
			if (l != 2) {
				System.out.print("\n----------\n");

			}
		}
		System.out.println();

	}

	public void jogar() {
		System.out.println("\nSUA VEZ:");
		int jogada;

		do {
			System.out.print("Escolha a posição para jogar: ");
			jogada = read.nextInt();
		} while (!contains(jogada));

		for (int l = 0; l < velha.length; l++) {
			for (int c = 0; c < velha[l].length; c++) {
				if (velha[l][c] == jogada) {
					velha[l][c] = 10;
				}
			}
		}
		apresentarTabuleiro();

		if (venceu(30)) {
			System.out.println("\n========== VOCÊ VENCEU! ==========\n");
		} else {
			jogadaPC();
		}
	}

	public void jogadaPC() {
		System.out.println("\nVEZ DO PC:");
		int jogada;

		do {
			jogada = random.nextInt(6) + 1;
		} while (!contains(jogada));

		for (int l = 0; l < velha.length; l++) {
			for (int c = 0; c < velha[l].length; c++) {
				if (velha[l][c] == jogada) {
					velha[l][c] = -10;
				}
			}
		}
		apresentarTabuleiro();

		if (venceu(-30)) {
			System.out.println("\n========== VOCÊ PERDEU! ==========\n");
		} else {
			jogar();
		}
	}

	public boolean contains(int numero) {
		for (int l = 0; l < velha.length; l++) {
			for (int c = 0; c < velha[l].length; c++) {
				if (velha[l][c] == numero) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean venceu(int totalNecessarioParaVencer) {
		int numeroLinha = 0, numeroColuna = 0, numeroDiagonal1 = 0;
		int numeroDiagonal2 = velha[2][0] + velha[1][1] + velha[0][2];

		for (int l = 0; l < velha.length; l++) {
			for (int c = 0; c < velha[l].length; c++) {
				numeroLinha += velha[l][c];
				numeroColuna += velha[c][l];

				if (l == c) {
					numeroDiagonal1 += velha[l][c];
				}
			}

			if (numeroLinha == totalNecessarioParaVencer || numeroColuna == totalNecessarioParaVencer
					|| numeroDiagonal1 == totalNecessarioParaVencer || numeroDiagonal2 == totalNecessarioParaVencer) {
				return true;
			}
			numeroLinha = 0;
			numeroColuna = 0;
		}

		return false;

	}

}
