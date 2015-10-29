package br.com.michaelnascimento.j12015.stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static javax.ejb.TransactionAttributeType.SUPPORTS;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class LicensePlateInformationService {

	@PersistenceContext
	private EntityManager em;

	@TransactionAttribute(SUPPORTS)
	public Map<Integer, Map<State, Map<Model, Long>>> ytdTotalPerModelPerStatePerYear() {
		final TypedQuery<LicensePlateInformation> query = em.createNamedQuery(
				"LicensePlateInformation.all",
				LicensePlateInformation.class);
		final List<LicensePlateInformation> infos = query.getResultList();

		YearMonth ym = YearMonth.now();
		int referenceYear = ym.getYear() - 1;
		int referenceMonth = ym.getMonthValue();

		return infos
				.stream()
				.filter(lpi -> lpi.ytd(referenceYear, referenceMonth))
				.collect(
						groupingBy(e -> e.getDate().getYear(),
								groupingBy(LicensePlateInformation::getState,
										groupingBy(LicensePlateInformation::getModel,
												counting())
								)
						)
				);
	}
}
