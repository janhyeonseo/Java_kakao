package cart;

import lombok.Data;

@Data
public class OrderVO {
  private  int     idxOrder ;
  private  String  orderG ;
  private  int     cart_id ;
  private  String  mid ;
  private  String  pid ;
  private  int     amount ;
  private  String  today ;
}
