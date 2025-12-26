function App() {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 p-8">
      {/* Header */}
      <header className="max-w-4xl mx-auto mb-12">
        <h1 className="text-5xl font-bold text-gray-800 mb-2">
          TaskHive 🐝
        </h1>
        <p className="text-xl text-gray-600">
          Proyecto configurado con Tailwind CSS v4
        </p>
      </header>

      {/* Cards Grid */}
      <div className="max-w-6xl mx-auto grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {/* Card 1 - Colors */}
        <div className="bg-white rounded-xl shadow-lg p-6 hover:shadow-2xl transition-shadow duration-300">
          <div className="w-12 h-12 bg-blue-500 rounded-lg mb-4"></div>
          <h2 className="text-2xl font-bold text-gray-800 mb-2">Colores</h2>
          <p className="text-gray-600 mb-4">
            Tailwind incluye una paleta completa de colores predefinidos.
          </p>
          <div className="flex gap-2">
            <div className="w-8 h-8 bg-red-500 rounded"></div>
            <div className="w-8 h-8 bg-green-500 rounded"></div>
            <div className="w-8 h-8 bg-yellow-500 rounded"></div>
            <div className="w-8 h-8 bg-purple-500 rounded"></div>
          </div>
        </div>

        {/* Card 2 - Typography */}
        <div className="bg-white rounded-xl shadow-lg p-6 hover:shadow-2xl transition-shadow duration-300">
          <div className="w-12 h-12 bg-green-500 rounded-lg mb-4 flex items-center justify-center text-white text-2xl font-bold">
            Aa
          </div>
          <h2 className="text-2xl font-bold text-gray-800 mb-2">Tipografía</h2>
          <p className="text-sm text-gray-600 mb-2">Tamaño pequeño</p>
          <p className="text-base text-gray-600 mb-2">Tamaño base</p>
          <p className="text-lg text-gray-600 mb-2">Tamaño grande</p>
          <p className="text-xl font-bold text-gray-800">Extra grande</p>
        </div>

        {/* Card 3 - Buttons */}
        <div className="bg-white rounded-xl shadow-lg p-6 hover:shadow-2xl transition-shadow duration-300">
          <div className="w-12 h-12 bg-purple-500 rounded-lg mb-4"></div>
          <h2 className="text-2xl font-bold text-gray-800 mb-4">Botones</h2>
          <div className="space-y-3">
            <button className="w-full px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors">
              Primario
            </button>
            <button className="w-full px-4 py-2 bg-gray-200 text-gray-800 rounded-lg hover:bg-gray-300 transition-colors">
              Secundario
            </button>
            <button className="w-full px-4 py-2 border-2 border-blue-600 text-blue-600 rounded-lg hover:bg-blue-50 transition-colors">
              Outline
            </button>
          </div>
        </div>

        {/* Card 4 - Spacing */}
        <div className="bg-white rounded-xl shadow-lg p-6 hover:shadow-2xl transition-shadow duration-300">
          <div className="w-12 h-12 bg-orange-500 rounded-lg mb-4"></div>
          <h2 className="text-2xl font-bold text-gray-800 mb-4">Espaciado</h2>
          <div className="space-y-2">
            <div className="bg-blue-100 p-2 rounded">padding-2</div>
            <div className="bg-blue-100 p-4 rounded">padding-4</div>
            <div className="bg-blue-100 p-6 rounded">padding-6</div>
          </div>
        </div>

        {/* Card 5 - Flexbox */}
        <div className="bg-white rounded-xl shadow-lg p-6 hover:shadow-2xl transition-shadow duration-300">
          <div className="w-12 h-12 bg-pink-500 rounded-lg mb-4"></div>
          <h2 className="text-2xl font-bold text-gray-800 mb-4">Flexbox</h2>
          <div className="flex items-center justify-between bg-gray-100 p-3 rounded-lg mb-2">
            <span className="text-sm">🎯</span>
            <span className="text-sm font-semibold">Centrado</span>
            <span className="text-sm">✨</span>
          </div>
          <div className="flex flex-col gap-2">
            <div className="bg-indigo-100 p-2 rounded text-sm">Item 1</div>
            <div className="bg-indigo-100 p-2 rounded text-sm">Item 2</div>
          </div>
        </div>

        {/* Card 6 - Effects */}
        <div className="bg-gradient-to-br from-purple-500 to-pink-500 rounded-xl shadow-lg p-6 hover:shadow-2xl hover:scale-105 transition-all duration-300 text-white">
          <div className="w-12 h-12 bg-white bg-opacity-30 rounded-lg mb-4 backdrop-blur"></div>
          <h2 className="text-2xl font-bold mb-2">Efectos ✨</h2>
          <p className="text-white text-opacity-90 mb-4">
            Gradientes, sombras, transiciones y más.
          </p>
          <div className="bg-white bg-opacity-20 rounded-lg p-3 backdrop-blur">
            <p className="text-sm font-semibold">Hover sobre esta tarjeta</p>
          </div>
        </div>
      </div>

      {/* Footer */}
      <footer className="max-w-4xl mx-auto mt-12 text-center">
        <div className="bg-white rounded-xl shadow-lg p-6">
          <p className="text-gray-600">
            ✅ <span className="font-semibold text-green-600">Tailwind CSS v4 está funcionando correctamente</span>
          </p>
          <p className="text-sm text-gray-500 mt-2">
            Si ves estos estilos, todo está configurado perfectamente
          </p>
        </div>
      </footer>
    </div>
  );
}

export default App;

