package arvoreB;

public class BTree {
	
	private Page root;//A raiz da página
	private int degree;//O grau
	
	public BTree(int degree) {
		this.degree = degree;
	}
	
	public void insert(Data key) {
		if(root == null) {
			root = new Page(degree, true);//Se for igual a null cria uma nova página
			root.addKey(key);//E adiciona a nova chave
			return;
		}
		
		Page page = navigateToLeaf(root, key);
		addOnLeaf(page, key);
		
	}
	
	public Page navigateToLeaf(Page page, Data key) {
		
		if(page.isLeaf()) {
			//addOnLeaf(page, key);
			return page;
		}else {//Senão for uma folha
			//navegar até a folha onde vai ser adicionado
			//chamar addOnLeaf
			for(int i=0; i<page.getnKeys(); i++) {
				if(key.getId() < page.getKeys(i).getId()) {
					navigateToLeaf(page.getChildren(i-1), key);
					return page;
				}
				if(i == page.getnKeys()-1) {
					navigateToLeaf(page.getChildren(i+1), key);
					return page;
				}
			}
		}
		return page;
	}
	
	public void addOnLeaf(Page page, Data key) {
		if(page.getnKeys() < degree) {
			page.addKey(key);
			return;
		}else {
			page = split(page, key);//Divisão da raiz em duas paginas e criação de uma nova raiz
			return;
		}
	}
	
	public Page split (Page currentPage, Data key) {

		Page p0/*Página pai*/ = new Page(degree, false);//Não vai ser folha
		Page p1/*filho da esqueda*/ = currentPage;
		Page p2/*filho da direita*/ = new Page(degree, p1.isLeaf());
		int j = 0, k = 0;
		Boolean alternativaFinal = true;
		
		int temp[] = new int[degree + 1];//Vai adicionar em um vetor temporário todos os elementos
		//Incluindo o novo elemento de forma ordenada 
		
		for(j =0; j<degree; j++) {
			if(key.getId() < currentPage.getKeys(j).getId()) {
				temp[j] = key.getId();
				alternativaFinal = false;
				break;
			}
		}
		
		if(alternativaFinal == true) {
			temp[j] = key.getId();
		}
		
		for(int i = 0; i <degree+1; i++) {
			if(i < j) {
				temp[i] = currentPage.getKeys(k).getId();
				k++;
			}else if(i > j) {
				temp[i] = currentPage.getKeys(k).getId();
				k++;
			}
		}
		
		
		k = 0;
		for(j = 0; j<degree/2; j++) {
			p1.addKey(new Data(temp[j]), k);
			k++;
		}
			
		if(j == degree/2) {
			p0.addKey(new Data(temp[j]), 0);
			p0.addChild(p1, 0);
			p0.addChild(p2, 1);
		}
		
		k = 0;
		for(j = 0; j<degree; j++) {
			p2.addKey(new Data(temp[j]), k);
			k++;
		}
		
		root = p0;
				
		//System.out.println(p0.getKeys(0).getId());

		
		/*if(key.getId() > currentPage.getKeys(j).getId()) {
			temp[j].setId(currentPage.getKeys(j).getId());
		}
		
		for(int i=0; i<degree+1; i++) {
			if(i<j) {
				temp[i].setId(currentPage.getKeys(k).getId());
				k++;
			}else if(i>j) {
				temp[i].setId(currentPage.getKeys(k).getId());
				k++;
			}
		}
		
		for(int i=0; i<degree+1; i++) {
			System.out.println(temp[i].getId());
		}
		
		/*
		for(int i = 0; i < degree + 1;i++) {
			temp[i] = 1;
		}*/
		

		
		//if()
		
		/*if(currentPage == this.root) {
			for(int i = 0; i < degree+1; i++) {
				if(j < (degree+1)/2) {
					p1.addKey(temp[i], j);
					j++;
				}else if(j == (degree+1)/2){
					p0.addKey(temp[i], 0);
					p0.addChild(p1, 0);
					p0.addChild(p2, 1);
				}else {
					p2.addKey(temp[i], k);
					k++;
				}
			}
		}*/
		
		/*//Coisa que devem ser feitas
		p0.addChild(p1, 0);
		p0.addChild(p2, 1);*/
		
		return p0;
 	}
	
	public void getData(int id) {
		
	}
	
	public void deleteData(int id) {
		
	}
	
	public Data findRightMost(Page page) {
		//Encontrar o elemnto mais a direita de um filho a esquerda 
		//Pra caso de remoção de um nó que não seja folha
		return null;
	}
	
	public void merge() {
		
	}
	
	public void reredistribute() {
		
	}
	public void inOrder() {
		inOrder(root);
	}
	
	public void inOrder(Page currentPage) {
		if(currentPage == null)
			return;
		
		for(int j = 0; j < currentPage.getnKeys(); j++) {
			inOrder(currentPage.getChildren(j));
			System.out.println(currentPage.getKeys(j).getId());
		}
		
		inOrder(currentPage.getChildren(currentPage.getnKeys()));

	}

}
