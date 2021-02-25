package arvoreB;

public class Page {
	
	//Uma página não folha deve ter no mínimo metade da lotação máxima
	
	private int degree;//O grau, quantidade de chabves que uma página pode ter
	private Data keys[];
	private Page children[];
	private int nKeys;//Número de chaves
	private Boolean leaf;//Ver se a página é uma folha ou não
	
	public Page(int degree, Boolean leaf) {
		this.degree = degree;
		this.leaf = leaf;
		this.keys = new Data[degree];
		this.children = new Page[degree + 1];//Quantidade de filhos
		this.nKeys = 0;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public Data getKeys(int pos) {//Para recuperar o item de uma posição
		return keys[pos];
	}

	public void setKeys(Data[] keys) {
		this.keys = keys;
	}

	public Page getChildren(int pos) {//Para recuperar o item de uma posição
		return children[pos];
	}

	public void addKey(Data key, int pos) {//Para setar o item em uma posição
		this.keys[pos] = key;
	}

	public int getnKeys() {
		return nKeys;
	}

	public void addChild(Page page, int pos) {//Para setar o item em uma posição
		this.children[pos] = page;
	}
	
	public void incrementNKeys() {
		this.nKeys++;
	}
	
	public void decrementNKeys() {
		this.nKeys--;
	}
	
	public int addKey(Data key) {
		if(nKeys == degree)//Verificar se o numero de chaves é igual ao grau
			return -1;  //Se for a página está lotada, senão pode adicionar
		
		int i = nKeys;
		while(i > 0 && key.getId() < keys[i-1].getId()) {
			keys[i] = keys[i-1];
			children[i+1] = children[i];
			i--;
		}
		keys[i] = key;
		nKeys++;//Para saber quantas chaves tem
		return i;
	}
	
	public Boolean isLeaf() {
		return leaf;
	}
	
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	
}
