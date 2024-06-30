package ForAll;
import java.util.ArrayList;
import java.util.List;
import com.vgnit.shop.entity.Product;

public class UniversalData 
{
	public static List<Product> cart;
	
	static
	{
		cart= new ArrayList<Product>();
	}
	
}
