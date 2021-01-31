package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Controller
@SessionAttributes("calendar")
public class GreetingController{

	public static final String WEEKDAY = "Sunday";

	@ModelAttribute("calendar")
	public Calendar getCalendar() {
		LocalDate now = LocalDate.now();
		return new Calendar(now.getYear(), now.getMonthValue());
	}

	@RequestMapping("/calendar")
	@GetMapping("/calendar")
	public String calendar(Model model, @ModelAttribute("calendar") Calendar calendar) {
		model.addAttribute("days", calendar.getDays());
		model.addAttribute("weekdays", Calendar.createWeekDayHeader(WEEKDAY));
		model.addAttribute("months", Calendar.getMonthsList());
		model.addAttribute("nowMonth", calendar.monthToString());
		model.addAttribute("years", Calendar.getYearsList());
		model.addAttribute("nowYear", calendar.getCurrentYear());
		model.addAttribute("holder", new Holder());
		return "calendar";
	}

	@GetMapping("/nextmonth")
	public String calendarSubmitNextMonth(Model model, @ModelAttribute("calendar") Calendar calendar){
		model.addAttribute("days", calendar.plusMonths(1));
		model.addAttribute("weekdays", Calendar.createWeekDayHeader(WEEKDAY));
		model.addAttribute("months", Calendar.getMonthsList());
		model.addAttribute("nowMonth", calendar.monthToString());
		model.addAttribute("years", Calendar.getYearsList());
		model.addAttribute("nowYear", calendar.getCurrentYear());
		model.addAttribute("holder", new Holder());
		return "calendar";
	}

	@GetMapping("/previousmonth")
	public String calendarSubmitPreviousMonth(Model model, @ModelAttribute("calendar") Calendar calendar){
		model.addAttribute("days", calendar.plusMonths(-1));
		model.addAttribute("weekdays", Calendar.createWeekDayHeader(WEEKDAY));
		model.addAttribute("months", Calendar.getMonthsList());
		model.addAttribute("nowMonth", calendar.monthToString());
		model.addAttribute("years", Calendar.getYearsList());
		model.addAttribute("nowYear", calendar.getCurrentYear());
		model.addAttribute("holder", new Holder());
		return "calendar";
	}

	@GetMapping("/nextyear")
	public String calendarSubmitNextYear(Model model, @ModelAttribute("calendar") Calendar calendar){
		model.addAttribute("days", calendar.plusMonths(12));
		model.addAttribute("weekdays", Calendar.createWeekDayHeader(WEEKDAY));
		model.addAttribute("months", Calendar.getMonthsList());
		model.addAttribute("nowMonth", calendar.monthToString());
		model.addAttribute("years", Calendar.getYearsList());
		model.addAttribute("nowYear", calendar.getCurrentYear());
		model.addAttribute("holder", new Holder());
		return "calendar";
	}

	@GetMapping("/previousyear")
	public String calendarSubmitPreviousYear(Model model, @ModelAttribute("calendar") Calendar calendar){
		model.addAttribute("days", calendar.plusMonths(-12));
		model.addAttribute("weekdays", Calendar.createWeekDayHeader(WEEKDAY));
		model.addAttribute("months", Calendar.getMonthsList());
		model.addAttribute("nowMonth", calendar.monthToString());
		model.addAttribute("years", Calendar.getYearsList());
		model.addAttribute("nowYear", calendar.getCurrentYear());
		model.addAttribute("holder", new Holder());
		return "calendar";
	}

	@RequestMapping("/event")
	public String eventCreate(Model model){
		model.addAttribute("event", new Event());
		return "test";
	}

	@PostMapping("/event")
	public String formSubmit(@ModelAttribute Event event, Model model, @ModelAttribute("calendar") Calendar calendar){
		model.addAttribute("event", event);
		return "test";
	}

	@PostMapping("/calendar")
	public String changeDateSubmit(@ModelAttribute Holder holder, Model model, @ModelAttribute("calendar") Calendar calendar){
		calendar.createMonth(holder.getHolderYear(), Calendar.monthToInt(holder.getMonthString()));

		/*
		Change the month and year displayed when the form is submitted.
		 */
		String monthDisplay = "";
		if (holder.getMonthString() != null) {
			monthDisplay = holder.getMonthString();
		}
		else {
			monthDisplay = calendar.monthToString();
		}

		model.addAttribute("days", calendar.getDays());
		model.addAttribute("weekdays", Calendar.createWeekDayHeader(WEEKDAY));
		model.addAttribute("months", Calendar.getMonthsList());
		model.addAttribute("nowMonth", monthDisplay);
		model.addAttribute("years", Calendar.getYearsList());
		model.addAttribute("nowYear", calendar.getCurrentYear());
		model.addAttribute("holder", new Holder());
		return "calendar";
	}
}