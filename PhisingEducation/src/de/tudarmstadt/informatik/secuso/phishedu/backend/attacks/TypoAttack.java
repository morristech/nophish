package de.tudarmstadt.informatik.secuso.phishedu.backend.attacks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import de.tudarmstadt.informatik.secuso.phishedu.backend.BackendController;
import de.tudarmstadt.informatik.secuso.phishedu.backend.PhishAttackType;
import de.tudarmstadt.informatik.secuso.phishedu.backend.PhishURLInterface;

/**
 * This Attack replaces the whole URL by a replace from phishtank
 * @author Clemens Bergmann <cbergmann@schuhklassert.de>
 *
 */
public class TypoAttack extends AbstractAttack {
	
	/**
	 * This constructor is required because of the implementation in {@link BackendController#getNextUrl()}
	 * @param object This Parmeter is discarded. It is replaced by a PhishTank URL
	 */
	public TypoAttack(PhishURLInterface object) {
		super(object);
	}

	@Override
	public PhishAttackType getAttackType() {
		return PhishAttackType.Typo;
	}
	
	@Override
	public String[] getParts() {
		String[] parts = super.getParts();
		ArrayList<String> adder = new ArrayList<String>(Arrays.asList(parts));
		String domain = adder.remove(3);
		int attackpos = new Random().nextInt(domain.length()-1);
		switch (new Random().nextInt(2)) {
		case 0:
			//swap
			String find = domain.substring(attackpos, attackpos+1);
			String replace = find.charAt(1)+""+find.charAt(0);
			domain=domain.replace(find, replace);
			break;

		default:
			//copy char
			String first = domain.substring(0,attackpos);
			String last = domain.substring(attackpos);
			domain=first+first.charAt(first.length()-1)+last;
			break;
		}
		
		adder.add(3, domain.toString());
		return adder.toArray(new String[0]);
	}
	
	@Override
	public boolean partClicked(int part) {
		return part==3;
	}
	
	@Override
	public List<Integer> getAttackParts() {
		List<Integer> result = super.getAttackParts();
		result.add(3);
		return result;
	}
}
