package business;

import java.util.*;

import integration.ArcaDao;
import integration.enumeratori.EnumSpecie;
import integration.pojo.AnimaleDto;

public class Arca {

	public boolean imbarca(AnimaleDto animale) {
		Map<Integer, AnimaleDto> mapAnimali = new HashMap<>();
		mapAnimali = ArcaDao.select();
		int id;
		int contAnimaliSpecie = 0;
		int MassimoNumeroAnimali = 2;
		if (animale == null) {
			System.out.println("non posso imbarcare un animale nullo");
			return false;
		}

		id = animale.getId();

		if (mapAnimali.containsKey(id)) {
			System.out.println("non posso imbarcare l'animale, è già presente lo stesso animale");
			return false;
		}

		contAnimaliSpecie = contoAnimaliSpecie(animale.getSpecie(), mapAnimali);

		if (contAnimaliSpecie >= MassimoNumeroAnimali) {
			System.out.println("non posso imbarcare l'animale, sono già presenti 2 animali della stessa specie");
			return false;
		}

		if (ArcaDao.insert(animale)) {
			System.out.println("imbarco andato a buonfine");
			return true;
		} else {
			return false;
		}

	}

	private int contoAnimaliSpecie(EnumSpecie specie, Map<Integer, AnimaleDto> mapAnimali) {
		int contoAnimali = 0;
		for (AnimaleDto animale : mapAnimali.values()) {
			if (specie == animale.getSpecie()) {
				contoAnimali++;
			}
		}
		return contoAnimali;

	}

	public List<AnimaleDto> getAnimaliImbarcati() {
		Map<Integer, AnimaleDto> animali = new HashMap<>();
		animali = ArcaDao.select();
		List<AnimaleDto> animaliLista = new ArrayList<>(animali.values());
		Collections.sort(animaliLista, new Comparator<AnimaleDto>() {
			public int compare(AnimaleDto animale1, AnimaleDto animale2) {
				if (animale1.getPeso() > animale2.getPeso()) {
					return 1;
				} else if (animale1.getPeso() < animale2.getPeso()) {
					return -1;
				} else {
					if (animale1.getId() > animale2.getId()) {
						return 1;
					} else {
						return -1;
					}
				}
			}
		});
		return animaliLista;
	}
	
	public List<EnumSpecie> getSpeci() {
		List<EnumSpecie> speci = new ArrayList<>();
		speci=ArcaDao.selectSpeci();
		
		return speci;
	}

	public AnimaleDto getAnimaleImbarcato(int id) {
		AnimaleDto animale;
		animale = ArcaDao.selectAnimale(id);
		return animale;
	}

	public Boolean sbarca(int idAnimale) {
		Map<Integer, AnimaleDto> animali = new HashMap<>();
		animali = ArcaDao.select();

		if (animali.containsKey(idAnimale)) {

			if (ArcaDao.delete(idAnimale)) {
				System.out.println("\nanimale sbarcato:");
				System.out.println("id:" + animali.get(idAnimale).getId() + " peso:" + animali.get(idAnimale).getPeso()
						+ " specie:" + animali.get(idAnimale).getSpecie());
				return true;
			}

		}

		System.out.println("\nanimale non trovato");
		return false;

	}

	

	public boolean updateAnimale(Integer id, Integer peso) {
		Map<Integer, AnimaleDto> animali = new HashMap<>();
		animali=ArcaDao.select();
		if (ArcaDao.update(id,peso)&& animali.containsKey(id)) {
			System.out.println("update andato a buonfine");
			return true;
		}
		return false;
		
	}

	
}