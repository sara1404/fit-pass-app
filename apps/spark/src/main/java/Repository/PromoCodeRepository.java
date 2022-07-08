package Repository;

import DataHandler.TemplateDataHandler;
import Interfaces.Repository.IPromoCodeRepository;
import Model.PromoCode;

import java.util.List;

public class PromoCodeRepository implements IPromoCodeRepository {

    private List<PromoCode> promoCodes;
    private TemplateDataHandler<PromoCode> promoCodeDataHandler;


    public PromoCodeRepository(TemplateDataHandler<PromoCode> promoCodeDataHandler){
        this.promoCodeDataHandler = promoCodeDataHandler;
        this.promoCodes = promoCodeDataHandler.readFromFile();
    }

    @Override
    public void addPromoCode(PromoCode promoCode) throws Exception{
        if(doesExist(promoCode))
            throw new Exception("Promo code with this id already exists!");
        promoCodes.add(promoCode);
        promoCodeDataHandler.writeToFile(promoCodes);
    }

    @Override
    public PromoCode findById(String id) {
        for (PromoCode code : promoCodes){
            if(code.getId().equals(id))
                return code;
        }
        return null;
    }

    @Override
    public void update(PromoCode promoCode) {
        PromoCode code = findById(promoCode.getId());
        code.setTimesToUse(promoCode.getTimesToUse());
        promoCodeDataHandler.writeToFile(promoCodes);

    }

    public boolean doesExist(PromoCode promoCode){
        for (PromoCode code: promoCodes){
            if(code.getId().equals(promoCode.getId()))
                return true;
        }
        return false;
    }
}
