var juiComponentArray = 
	[
		'ui.tree',
		'ui.accordion',
		'ui.autocomplete',
		'ui.button',
		'ui.colorpicker',
		'ui.combo',
		'ui.datepicker',
		'ui.dropdown',
		'ui.layout',
		'ui.modal',
		'ui.notify',
		'ui.paging',
		'ui.property',
		'ui.slider',
		'ui.splitter',
		'ui.switch',
		'ui.tab',
		'ui.timepicker',
		'ui.tooltip',
		'ui.window'
	];

var juiGridArray = 
	[
		'grid.base',
		'grid.column',
		'grid.row',
		'grid.table',
		'grid.xtable'
	];

var juiUtilArray = 
	[
		'util.base',
		'util.color',
		'util.dom',
		'util.math'
	];

var jui_ui_tree = null;
var jui_ui_accordion = null;
var jui_ui_autocomplete = null;
var jui_ui_button = null;
var jui_ui_colorpicker = null;
var jui_ui_combo = null;
var jui_ui_datepicker = null;
var jui_ui_dropdown = null;
var jui_ui_layout = null;
var jui_ui_modal = null;
var jui_ui_notify = null;
var jui_ui_paging = null;
var jui_ui_property = null;
var jui_ui_slider = null;
var jui_ui_splitter = null;
var jui_ui_switch = null;
var jui_ui_tab = null;
var jui_ui_timepicker = null;
var jui_ui_tooltip = null;
var jui_ui_window = null;

var jui_grid_base = null;
var jui_grid_column = null;
var jui_grid_row = null;
var jui_grid_table = null;
var jui_grid_xtable = null;

var jui_util_base = null;
var jui_util_color = null;
var jui_util_dom = null;
var jui_util_math = null;

var jui_chart_build = null;

jui.ready(juiComponentArray, function(tree, accordion, autocomplete, button, colorpicker, combo, datepicker, dropdown, layout, modal, notify, paging, property, slider, splitter, jui_ui_switch, tab, timepicker, tooltip, window) {
	jui_ui_tree = tree;
	jui_ui_accordion = accordion;
	jui_ui_autocomplete = autocomplete;
	jui_ui_button = button;
	jui_ui_colorpicker = colorpicker;
	jui_ui_combo = combo;
	jui_ui_datepicker = datepicker;
	jui_ui_dropdown = dropdown;
	jui_ui_layout = layout;
	jui_ui_modal = modal;
	jui_ui_notify = notify;
	jui_ui_paging = paging;
	jui_ui_property = property;
	jui_ui_slider = slider;
	jui_ui_splitter = splitter;
	jui_ui_switch = jui_ui_switch;
	jui_ui_tab = tab;
	jui_ui_timepicker = timepicker;
	jui_ui_tooltip = tooltip;
	jui_ui_window = window;
});

jui.ready(juiGridArray, function(base, column, row, table, xtable){
	jui_grid_base = base;
	jui_grid_column = column;
	jui_grid_row = row;
	jui_grid_table = table;
	jui_grid_xtable = xtable;
});

jui.ready(juiUtilArray, function(base, color, dom, math){
//	jui_util_scale = scale;
//	jui_util_svg = svg;
	jui_util_base = base;
	jui_util_color = color;
	jui_util_dom = dom;
	jui_util_math = math;
//	jui_util_time = time;
});

//var tui-chart = tui.chart; /* namespace */