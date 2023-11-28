package F_OO.Heranca;

public class Jogador {

    public int life = 100;
    public int x;
    public int y;

    public Jogador(){

    }
    public Jogador(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean atacar(Jogador oponente){

        int deltaX = Math.abs(x - oponente.x);
        int deltaY = Math.abs(y - oponente.y);

        if (deltaX==0 && deltaY == 1){
            oponente.life -= 10;
            return true;
        } else if (deltaX == 1 && deltaY == 0) {
            oponente.life -= 10;
            return true;
        }
        return false;
    }
    public boolean andar(Direcao direcao){

        switch (direcao) {
            case NORTE -> y++;
            case SUL -> y--;
            case LESTE -> x++;
            case OESTE -> x--;
        }
        return true;
    }
}
